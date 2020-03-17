package com.foryou.tax.process.eleinvoice;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.api.bean.ErrorBean;
import com.foryou.tax.api.bean.error.ErrorDesc;
import com.foryou.tax.api.bean.error.ErrorInfo;
import com.foryou.tax.dao.eleinvoice.EleInvoiceMapper;
import com.foryou.tax.pojo.eleinvoice.EleInvoice;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.eleinvoice.EleInvoiceDetailService;
import com.foryou.tax.service.eleinvoice.EleInvoiceService;
import com.foryou.tax.util.LoggerUtils;
import com.foryou.tax.util.eleinvoice.EleInvoiceSubmitXmlUtil;
import com.foryou.tax.util.eleinvoice.GetMarginXmlUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/17
 * @description : 电子发票process处理类
 */
@Service
public class EleInvoiceProcess extends BaseProcess {

    @Autowired
    private EleInvoiceService eleInvoiceService;

    @Autowired
    private EleInvoiceDetailService eleInvoiceDetailService;

    public void eleInvoiceInfoSubmit(HttpServletRequest request, HttpServletResponse response, List<ALLInvoiceInfo> eleInvoiceDataList){

        /**
         * 查询发票余量
         */
        String getMargin = GetMarginXmlUtil.getMarginXml();
        //发送
        JSONObject marginJsonObject = GetMarginXmlUtil.postData(getMargin);
        String res_Message = ""; //错误信息记录
        LoggerUtils.debug(getClass(), getMargin);
        ErrorBean errorBean = new ErrorBean();
        ErrorInfo errorInfo = new ErrorInfo();
        ErrorDesc errorDesc = new ErrorDesc();
        if ("false".equals(marginJsonObject.getString("flag"))) {
            errorDesc.setMessage(marginJsonObject.getString("errorMessage"));
            errorDesc.setCode("false");
            errorInfo.setErrDesc(errorDesc);
            errorInfo.setType("false");
            errorBean.setError(errorInfo);
            writeClientJson(response, errorBean, null);
        }else {
            /**
             * 比较发票余量和这次开票的数量，余量不够不允许开票
             */
            int acrEleNumber = Integer.parseInt(marginJsonObject.getString("acrEleNumber"));
            if (acrEleNumber < eleInvoiceDataList.size()) {
                errorDesc.setMessage("发票余量不足此次开票，请检查");
                errorDesc.setCode("false");
                errorInfo.setErrDesc(errorDesc);
                errorInfo.setType("false");
                errorBean.setError(errorInfo);
                writeClientJson(response, errorBean, null);
            }else{
                for (int i = 0; i < eleInvoiceDataList.size(); i++) {
                    /**
                     * 先把发票头行表信息存到电子发票中间头行表中
                     * 先判断这条发票是否已经执行过开票，也就是电子发票中间表是否存在记录
                     *
                     */
                    EleInvoice eleInvoice = new EleInvoice();
                    eleInvoice.setInvoiceId(eleInvoiceDataList.get(i).getInvoiceId());

                    List<EleInvoice> eleInvoices = eleInvoiceService.select(eleInvoice);

                    /**
                     * 如果出现"请维护对应的税收分类编码"这种错误，先删掉 acr_ele_invoice_hd 表中这条数据
                     */
                    for(int j = 0; j < eleInvoices.size(); j++){
                        if("请维护对应的税收分类编码".equals(eleInvoices.get(j).getErrorMessage())){
                            long eleId = eleInvoices.get(j).getEleInvoiceId();
                            eleInvoiceService.deleteData(eleId);
                        }
                    }
                    /**
                     * 删完之后再重新查询一下
                     */
                    eleInvoices = eleInvoiceService.select(eleInvoice);

                    if (eleInvoices.size() == 0) {
                        data = insertEleInvoiceInfo(request, eleInvoiceDataList.get(i));

                        if (data != null){
                            //调用发送接口报文封装类
                            String xml = EleInvoiceSubmitXmlUtil.eleInvoiceSubmitXml(eleInvoiceDataList.get(i), eleInvoiceService, eleInvoiceDetailService);

                            //调用发送接口工具类
                            JSONObject jsonObject = EleInvoiceSubmitXmlUtil.postData(xml);
                            if (jsonObject == null || 0 == jsonObject.size()) {
                                String errorMessage = "金税返回错误，null或者{}，请检查！";
                                EleInvoice eleInvoice1 = new EleInvoice();
                                eleInvoice1.setInvoiceId(eleInvoiceDataList.get(i).getInvoiceId());
                                eleInvoice1.setErrorCode("FAILURE");
                                eleInvoice1.setErrorMessage(errorMessage);
                                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                                eleInvoiceService.updateInvoiceHdErrorStatus(eleInvoice1);
                            } else if ("false".equals(jsonObject.getString("flag"))) {
//                            responseData.setSuccess(false);
//                            responseData.setMessage(jsonObject.getString("errorMessage"));
//                            throw new ResMessageException(jsonObject.getString("errorMessage"));
                                /**
                                 * 如果开电子发票报错，则把报错信息放入AcrInvoiceHd的Taxcode和Taxmsg
                                 * add by zwg
                                 */
                                String errorCode = jsonObject.getString("statusCode");
                                String errorMessage = jsonObject.getString("errorMessage");

                                EleInvoice eleInvoice1 = new EleInvoice();
                                eleInvoice1.setInvoiceId(eleInvoiceDataList.get(i).getInvoiceId());
                                eleInvoice1.setErrorCode(errorCode);
                                eleInvoice1.setErrorMessage(errorMessage);
                                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                                eleInvoiceService.updateInvoiceHdErrorStatus(eleInvoice1);
                            } else {
                                //更新电子发票状态，表示已经传入金税接口
                                eleInvoiceService.updateAcrEleHdStatus(eleInvoiceDataList.get(i).getInvoiceId());

                                //开票成功结束后，更新发票余量
                                int num = acrEleNumber - eleInvoiceDataList.size();

                                /**
                                 * FND_COMPANIES 表
                                 */
                                Long companyId = iRequest.getCompanyId();
                                AcrEleInvoiceHd acrEleInvoiceHd = new AcrEleInvoiceHd();
                                acrEleInvoiceHd.setAcrEleNumber(num);
                                acrEleInvoiceHd.setCompanyId(companyId);
                                eleInvoiceService.updateAceEleNumber(acrEleInvoiceHd);
                            }
                        }
                    }
                }
            }
        }

        //获取所有的错误信息
        for(int k = 0; k < eleInvoiceDataList.size(); k++){
            ALLInvoiceInfo hd2 = new ALLInvoiceInfo();
            hd2 = self().selectByPrimaryKey(iRequest, eleInvoiceDataList.get(k));
            EleInvoice eleInvoice = new EleInvoice();
            eleInvoice.setInvoiceId(eleInvoiceDataList.get(k).getInvoiceId());
            List<EleInvoice> eleInvoices = eleInvoiceService.select(eleInvoice);
            if((eleInvoices.size() > 0 && eleInvoices.get(0) != null &&
                    eleInvoices.get(0).getErrorMessage() != null) || hd2.getTaxMsg() != null){
                res_Message = res_Message + "<br/>" + hd2.getDocumentNumber();
            }

        }
        //设置自定义返回信息
        if (res_Message != "" && responseData.getMessage() == null) {
            responseData.setSuccess(false);
            responseData.setMessage(res_Message);
        }
    }



    /**
     * ？？？？？？是记录在ALL_Invoice_Info好，还是记录在 ELE_INVOICE？？？？
     * 如果是以下报错，则插入acr_invoice_hd
     *
     * 1。开电子发票没有存在开票手机号码
     * 2。电子发票只能开普票
     * 3。开票人名称过长，不符合
     * 如果是以下报错，则插入acr_ele_invoice_hd
     *    没有对应的税收分类编码
     * 因为税收编码是关联在现金流表里，无法拆到acr_invoice_hd
     *
     */
    public ResponseData insertEleInvoiceInfo(HttpServletRequest request, ALLInvoiceInfo acrInvoiceHd){
        ResponseData responseData = new ResponseData(true);
        String saleTax = "";
        try {
            Properties properties = new Properties();
            InputStream is = PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(is);
            saleTax = properties.getProperty("acr.saleTax");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ALLInvoiceInfo hd = new ALLInvoiceInfo();
        hd = self().selectByPrimaryKey(iRequest, acrInvoiceHd);

        //先判断这条发票是否已经执行过开票，也就是电子发票中间表是否存在记录
        EleInvoice eleInvoice = new EleInvoice();
        eleInvoice.setInvoiceId(hd.getInvoiceId());
        List<EleInvoice> eleInvoiceHds = eleInvoiceService.select(eleInvoice);

        if (eleInvoiceHds.size() > 0) {
            return responseData;
        } else {
            //流水号
            String swno = eleInvoiceService.getSwnoDocumentNumber(iRequest.getUserId(), iRequest.getCompanyId());
            //购货方企业类型
            String bpClass = eleInvoiceService.getBpClass(hd.getBpId());
            String custType = "";
            if ("NP".equals(bpClass)) {
                // 03 代表个人
                custType = "03";
            } else {
                // 01 代表法人
                custType = "01";
            }
            //获取电话号码
            String custTelephone = eleInvoiceService.getCellPhone(hd.getBpId());
            // if ("03".equals(custType)) {
            if ("".equals(custTelephone) || custTelephone == null) {
                    /*responseData.setSuccess(false);
                    responseData.setMessage("开个人电子发票必须存在手机号码");
                    return responseData;*/
                //update by zhuxianfei 20181228
                //throw new CallException("开电子发票必须存在手机号码");
                /**
                 * 如果开电子发票报错，则把报错信息放入AcrInvoiceHd的Taxcode和Taxmsg
                 * add by zwg
                 */
                String errorCode = "";
                String errorMessage = "开电子发票必须存在手机号码";

                AcrInvoiceHd a = new AcrInvoiceHd();
                a.setInvoiceHdId(hd.getInvoiceHdId());
                a.setTaxCode(errorCode);
                a.setTaxMsg(errorMessage);
                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                acrEleInvoiceHdMapper.updateInvoiceHdErrorStatus(a);
                return null;
            }
            // }
            //获取邮箱
            String email = eleInvoiceService.getEmail(hd.getBpId());

            //判断发票类型，如果是专票，不允许开
            if ("0".equals(hd.getInvoiceKind())) {
               /* responseData.setSuccess(false);
                responseData.setMessage("电子发票只能开普票");
                return responseData;*/
                //update by zhuxianfei 20181228
//                throw new CallException("电子发票只能开普票");
                /**
                 * 如果开电子发票报错，则把报错信息放入AcrInvoiceHd的Taxcode和Taxmsg
                 * add by zwg
                 */
                String errorCode = "";
                String errorMessage = "电子发票只能开普票";

                AcrInvoiceHd a = new AcrInvoiceHd();
                a.setInvoiceHdId(hd.getInvoiceHdId());
                a.setTaxCode(errorCode);
                a.setTaxMsg(errorMessage);
                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                acrEleInvoiceHdMapper.updateInvoiceHdErrorStatus(a);
                return null;

            }

            //获取开票人名称
            String name = eleInvoiceService.getName(hd.getCreatedBy());
            //获取确认人名称
            String fhrName = eleInvoiceService.getName(hd.getConfirmedBy());
            System.out.println(name.length());
            if (name.length() > 4 || fhrName.length() > 4) {
//                throw new CallException("开票人名称过长，不符合");
                /**
                 * 如果开电子发票报错，则把报错信息放入AcrInvoiceHd的Taxcode和Taxmsg
                 * add by zwg
                 */
                String errorCode = "";
                String errorMessage = "开票人或确认人名称过长，不符合";

                AcrInvoiceHd a = new AcrInvoiceHd();
                a.setInvoiceHdId(hd.getInvoiceHdId());
                a.setTaxCode(errorCode);
                a.setTaxMsg(errorMessage);
                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                acrEleInvoiceHdMapper.updateInvoiceHdErrorStatus(a);
                return null;
            }

            //插入电子发票中间头表
            AcrEleInvoiceHd acrEleInvoiceHd = new AcrEleInvoiceHd();
            acrEleInvoiceHd.setInvoiceHdId(hd.getInvoiceHdId());
            acrEleInvoiceHd.setDocumentType("ACR_ELE_INVOICE");
            acrEleInvoiceHd.setDocumentCategory("ACR_ELE_INVOICE");
            acrEleInvoiceHd.setSwno(swno);  //流水号
            acrEleInvoiceHd.setSaleTax(saleTax);
            acrEleInvoiceHd.setCustName(hd.getBpName());
            acrEleInvoiceHd.setCustTaxNo(hd.getBpTaxRegistryNum());
            acrEleInvoiceHd.setCustAddr(hd.getBpAddressPhoneNum());
            acrEleInvoiceHd.setCustTelephone(custTelephone);
            acrEleInvoiceHd.setCustPhone("");
            acrEleInvoiceHd.setCustEmail(email);
            acrEleInvoiceHd.setCustBankAccount(hd.getBpBankAccount());
            acrEleInvoiceHd.setCustType(custType);
            acrEleInvoiceHd.setInvoMemo(hd.getDescription());
            //acrEleInvoiceHd.setInvType(hd.getInvoiceKind());
            acrEleInvoiceHd.setInvType("3");
            acrEleInvoiceHd.setBillDate(new Date());
            acrEleInvoiceHd.setThdh("");
            acrEleInvoiceHd.setBillType(1L);  //1代表开正票
            acrEleInvoiceHd.setSpecialRedFlag("0");   //默认0  代表正常冲红，电子发票
            acrEleInvoiceHd.setOperationCode("10");   //正票正常开具
            acrEleInvoiceHd.setKpy(name);  //用户名称
            //acrEleInvoiceHd.setSky(name);  //同开票人
            acrEleInvoiceHd.setSky("金海燕");  //写死
            //acrEleInvoiceHd.setSky(Skr);  //开票人
            acrEleInvoiceHd.setFhr(fhrName);  //同开票人
            acrEleInvoiceHd.setYfpdm("");  //原发票代码 红冲使用，这里是空
            acrEleInvoiceHd.setYfphm("");  //原发票号码 红冲使用，这里是空
            acrEleInvoiceHd.setChyy("0");  //正常票填0
            acrEleInvoiceHd.setStatus("NEW");
            acrEleInvoiceHd = iAcrEleInvoiceHdService.insertSelective(iRequest, acrEleInvoiceHd);

            //插入电子发票中间行表
            List<AcrInvoiceLn> acrInvoiceLns = acrInvoiceLnMapper.selectAcrInvoiceLnDetail(hd.getInvoiceHdId());
            for (int i = 0; i < acrInvoiceLns.size(); i++) {
                //获取合同ID和合同编号
                List<Map> list = acrEleInvoiceHdMapper.getContractDetail(acrInvoiceLns.get(i).getCashflowId());
                //获取税收分类编码
                AcrEleInvoiceHd invoiceHd = new AcrEleInvoiceHd();
                invoiceHd.setCashflowId(acrInvoiceLns.get(i).getCashflowId());
                invoiceHd.setCfType(acrInvoiceLns.get(i).getCfType());
                String code = acrEleInvoiceHdMapper.getTaxClassNum(invoiceHd);
                //update by zhuxianfei 20181228
                if ("".equals(code) || code == null) {

//                    throw new CallException("请维护对应的税收分类编码");

                    String errorCode = "";
                    String errorMessage = "请维护对应的税收分类编码";

                    acrEleInvoiceHd.setInvoiceHdId(hd.getInvoiceHdId());
                    acrEleInvoiceHd.setErrorCode(errorCode);
                    acrEleInvoiceHd.setErrorMessage(errorMessage);
                    //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                    acrEleInvoiceHdMapper.updateAcrEleHdErrorStatus(acrEleInvoiceHd);
                    return null;
                }

                AcrEleInvoiceLn acrEleInvoiceLn = new AcrEleInvoiceLn();
                acrEleInvoiceLn.setEleInvoiceHdId(acrEleInvoiceHd.getEleInvoiceHdId());
                acrEleInvoiceLn.setInvoiceHdId(acrInvoiceLns.get(i).getInvoiceHdId());
                acrEleInvoiceLn.setInvoiceLnId(acrInvoiceLns.get(i).getInvoiceLnId());
                acrEleInvoiceLn.setContractId(Long.valueOf(String.valueOf(list.get(0).get("contractId"))));
                acrEleInvoiceLn.setCashflowId(acrInvoiceLns.get(i).getCashflowId());
                acrEleInvoiceLn.setBillNo(String.valueOf(list.get(0).get("contractNumber")));
                acrEleInvoiceLn.setName(acrInvoiceLns.get(i).getProductName());
                acrEleInvoiceLn.setCode(code); //税收分类编码
                acrEleInvoiceLn.setLineType("0");
                acrEleInvoiceLn.setSpec(acrInvoiceLns.get(i).getSpecification());
                //acrEleInvoiceLn.setUnit(acrInvoiceLns.get(i).getUom());
                acrEleInvoiceLn.setUnit("台");
                acrEleInvoiceLn.setTaxRate(acrInvoiceLns.get(i).getTaxTypeRate());
                acrEleInvoiceLn.setQuantity(acrInvoiceLns.get(i).getQuantity());
                acrEleInvoiceLn.setTaxPrice(acrInvoiceLns.get(i).getPrice());
                acrEleInvoiceLn.setTotalAmount(acrInvoiceLns.get(i).getTotalAmount());
                acrEleInvoiceLn.setYhzcbs("0");
                acrEleInvoiceLn.setYhzcnr("");
                acrEleInvoiceLn.setLslbs(null);
                acrEleInvoiceLn.setZxbm("");
                acrEleInvoiceLn.setKce(null);
                iAcrEleInvoiceLnService.insertSelective(iRequest, acrEleInvoiceLn);
            }
        }
        return responseData;
    }
}
