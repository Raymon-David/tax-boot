package com.foryou.tax.process.eleinvoice;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.api.bean.ErrorBean;
import com.foryou.tax.api.bean.error.ErrorDesc;
import com.foryou.tax.api.bean.error.ErrorInfo;
import com.foryou.tax.api.constant.EleErrorEnum;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceDetail;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.eleinvoice.EleInvoiceDetailService;
import com.foryou.tax.service.eleinvoice.EleInvoiceInfoService;
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
    private EleInvoiceInfoService eleInvoiceInfoService;

    @Autowired
    private EleInvoiceDetailService eleInvoiceDetailService;

    public void eleInvoiceInfoSubmit(HttpServletRequest request, HttpServletResponse response, List<AllInvoiceInfo> allInvoiceDataList){

        /**
         * 查询发票余量
         */
        String getMargin = GetMarginXmlUtil.getMarginXml();
        /**
         * 调用请求接口
         */
        JSONObject marginJsonObject = GetMarginXmlUtil.postData(getMargin);

        ErrorBean errorBean = new ErrorBean();
        ErrorInfo errorInfo = new ErrorInfo();
        ErrorDesc errorDesc = new ErrorDesc();
        if ("false".equals(marginJsonObject.getString("flag"))) {
            errorDesc.setMessage(marginJsonObject.getString("errorMessage"));
            errorDesc.setCode("false");
            errorInfo.setErrDesc(errorDesc);
            errorInfo.setType("error");
            errorBean.setError(errorInfo);
            writeClientJson(response, errorBean, null);
        }else {
            /**
             * 比较发票余量和这次开票的数量，余量不够不允许开票
             */
            int acrEleNumber = Integer.parseInt(marginJsonObject.getString("acrEleNumber"));
            if (acrEleNumber < allInvoiceDataList.size()) {
                errorDesc.setMessage(EleErrorEnum.E_E_2001.getErrorMsg());
                errorDesc.setCode(EleErrorEnum.E_E_2001.getErrorCode());
                errorInfo.setErrDesc(errorDesc);
                errorInfo.setType("error");
                errorBean.setError(errorInfo);
                writeClientJson(response, errorBean, null);
            }else{
                for (int i = 0; i < allInvoiceDataList.size(); i++) {
                    /**
                     * 先把发票头行表信息存到电子发票中间头行表中
                     * 先判断这条发票是否已经执行过开票，也就是电子发票中间表是否存在记录
                     *
                     */
                    EleInvoiceInfo eleInvoiceInfo = new EleInvoiceInfo();
                    eleInvoiceInfo.setInvoiceId(allInvoiceDataList.get(i).getInvoiceId());

                    List<EleInvoiceInfo> eleInvoices = eleInvoiceInfoService.select(eleInvoiceInfo);

                    /**
                     * 如果出现"请维护对应的税收分类编码"这种错误
                     * 先删掉 ele_invoice_info , ele_invoice_detail 中这条数据
                     */
                    for(int j = 0; j < eleInvoices.size(); j++){
                        if("请维护对应的税收分类编码".equals(eleInvoices.get(j).getEleInvoiceStatusName())){
                            long eleId = eleInvoices.get(j).getEleInvoiceId();
                            eleInvoiceInfoService.deleteData(eleId);
                            eleInvoiceDetailService.deleteData(eleId);
                        }
                    }
                    /**
                     * 删完之后再重新查询一下
                     */
                    eleInvoices = eleInvoiceInfoService.select(eleInvoiceInfo);

                    if (eleInvoices.size() == 0) {
                        data = insertEleInvoiceInfo(request, response, allInvoiceDataList.get(i));

                        if (data != null){
                            //调用发送接口报文封装类
                            String xml = EleInvoiceSubmitXmlUtil.eleInvoiceSubmitXml(allInvoiceDataList.get(i), eleInvoiceInfoService, eleInvoiceDetailService);

                            //调用发送接口工具类
                            JSONObject jsonObject = EleInvoiceSubmitXmlUtil.postData(xml);
                            if (jsonObject == null || 0 == jsonObject.size()) {
                                EleInvoiceInfo eleInvoiceInfo1 = new EleInvoiceInfo();
                                eleInvoiceInfo1.setInvoiceId(allInvoiceDataList.get(i).getInvoiceId());

                                eleInvoiceInfo1.setInvoiceInterfaceTaxCode(EleErrorEnum.E_E_2002.getErrorCode());
                                eleInvoiceInfo1.setInvoiceInterfaceTaxMessage(EleErrorEnum.E_E_2002.getErrorMsg());
                                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                                eleInvoiceInfoService.updateEleInvoiceTaxError(eleInvoiceInfo1);

                                errorDesc.setMessage(EleErrorEnum.E_E_2002.getErrorMsg());
                                errorDesc.setCode(EleErrorEnum.E_E_2002.getErrorCode());
                                errorInfo.setErrDesc(errorDesc);
                                errorInfo.setType("error");
                                errorBean.setError(errorInfo);
                                writeClientJson(response, errorBean, null);
                            } else if ("false".equals(jsonObject.getString("flag"))) {
                                /**
                                 * 凡是
                                 * 开电子发票金税返回报错
                                 * 则把报错信息放入EleInvoiceInfo 的 invoiceInterfaceTaxCode and invoiceInterfaceTaxMessage
                                 *
                                 */
                                String errorCode = jsonObject.getString("statusCode");
                                String errorMessage = jsonObject.getString("errorMessage");

                                EleInvoiceInfo eleInvoiceInfo1 = new EleInvoiceInfo();
                                eleInvoiceInfo1.setInvoiceId(allInvoiceDataList.get(i).getInvoiceId());
                                eleInvoiceInfo1.setInvoiceInterfaceTaxCode(errorCode);
                                eleInvoiceInfo1.setInvoiceInterfaceTaxMessage(errorMessage);
                                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                                eleInvoiceInfoService.updateEleInvoiceTaxError(eleInvoiceInfo1);

                                errorDesc.setMessage(errorMessage);
                                errorDesc.setCode(errorCode);
                                errorInfo.setErrDesc(errorDesc);
                                errorInfo.setType("error");
                                errorBean.setError(errorInfo);
                                writeClientJson(response, errorBean, null);
                            } else {
                                //更新电子发票状态，表示已经传入金税接口
                                eleInvoiceInfoService.updateAcrEleHdStatus(allInvoiceDataList.get(i).getInvoiceId());

                                //开票成功结束后，更新发票余量
                                int num = acrEleNumber - allInvoiceDataList.size();

                                /**
                                 * FND_COMPANIES 表
                                 */
                                Long companyId = iRequest.getCompanyId();
                                AcrEleInvoiceHd acrEleInvoiceHd = new AcrEleInvoiceHd();
                                acrEleInvoiceHd.setAcrEleNumber(num);
                                acrEleInvoiceHd.setCompanyId(companyId);
                                eleInvoiceInfoService.updateAceEleNumber(acrEleInvoiceHd);
                            }
                        }
                    }
                }
            }
        }
    }



    /**
     *
     * 如果是以下报错，则插入 ELE_INVOICE_INFO 表中 INVOICE_INTERFACE_TAX_CODE 和 INVOICE_INTERFACE_TAX_MESSAGE
     *
     * 1。开电子发票没有存在开票手机号码
     * 2。电子发票只能开普票
     * 3。开票人名称过长，不符合
     * 4。没有对应的税收分类编码
     *
     */
    public void insertEleInvoiceInfo(HttpServletRequest request, HttpServletResponse response, AllInvoiceInfo allInvoiceInfo){

        String saleTax = "";
        ErrorBean errorBean = new ErrorBean();
        ErrorInfo errorInfo = new ErrorInfo();
        ErrorDesc errorDesc = new ErrorDesc();

        try {
            Properties properties = new Properties();
            InputStream is = PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(is);
            saleTax = properties.getProperty("eleinvoice.saleTax");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 先判断这条发票是否已经执行过开票，也就是电子发票表是否存在记录
         */
        EleInvoiceInfo eleInvoiceInfo = new EleInvoiceInfo();
        eleInvoiceInfo.setInvoiceId(allInvoiceInfo.getInvoiceId());
        List<EleInvoiceInfo> eleInvoiceHds = eleInvoiceInfoService.queryAllData(eleInvoiceInfo);

        if (eleInvoiceHds.size() > 0) {
            errorDesc.setMessage(EleErrorEnum.E_E_2000.getErrorMsg());
            errorDesc.setCode(EleErrorEnum.E_E_2000.getErrorCode());
            errorInfo.setErrDesc(errorDesc);
            errorInfo.setType("error");
            errorBean.setError(errorInfo);
            writeClientJson(response, errorBean, null);
        } else {
            /**
             * 流水号
             * 关键字段不能为空，必须唯一 由数字、字母、下划线组成 字段固定长度是20位
             * 规则 EI_年月日_1000000001
             */
            String swno = eleInvoiceInfoService.getSwnoDocumentNumber(iRequest.getUserId(), iRequest.getCompanyId());
            /**
             * 购货方企业类型
             * 查询开票对象表
             */
            String bpClass = eleInvoiceInfoService.getBpClass(allInvoiceInfo.getObjectCode());
            String custType = null;
            /**
             * 01：企业
             * 02：机关执业单位
             * 03：个人
             * 04：其他
             */
            if ("NP".equals(bpClass)) {
                custType = "03";
            } else {
                custType = "01";
            }
            /**
             * 获取电话号码
             * 查询开票对象表
             */
            String custTelephone = eleInvoiceInfoService.getCellPhone(allInvoiceInfo.getObjectCode());
            if ("".equals(custTelephone) || custTelephone == null) {
                /**
                 * 如果开电子发票报错，则把报错信息放入 ELE_INVOICE_INFO 表中 INVOICE_INTERFACE_TAX_CODE 和 INVOICE_INTERFACE_TAX_MESSAGE
                 */
                EleInvoiceInfo a = new EleInvoiceInfo();
                a.setInvoiceId(allInvoiceInfo.getInvoiceId());
                a.setInvoiceInterfaceTaxCode(EleErrorEnum.E_E_2003.getErrorCode());
                a.setInvoiceInterfaceTaxMessage(EleErrorEnum.E_E_2003.getErrorMsg());
                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                eleInvoiceInfoService.updateEleInvoiceTaxError(a);
            }
            /**
             * 获取邮箱
             * 查询开票对象表
             */
            String email = eleInvoiceInfoService.getEmail(allInvoiceInfo.getObjectCode());

            /**
             * 判断发票类型，如果是专票，不允许开
             */
            if ("0".equals(allInvoiceInfo.getInvoiceKind())) {

                EleInvoiceInfo a = new EleInvoiceInfo();
                a.setInvoiceId(allInvoiceInfo.getInvoiceId());
                a.setInvoiceInterfaceTaxCode(EleErrorEnum.E_E_2004.getErrorCode());
                a.setInvoiceInterfaceTaxMessage(EleErrorEnum.E_E_2004.getErrorMsg());
                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                eleInvoiceInfoService.updateEleInvoiceTaxError(a);
            }

            //获取开票人名称
            String name = allInvoiceInfo.getIssuer().toString();
            //获取确认人名称
            String fhrName = allInvoiceInfo.getReviewer().toString();
            System.out.println(name.length());
            if (name.length() > 4 || fhrName.length() > 4) {

                EleInvoiceInfo a = new EleInvoiceInfo();
                a.setInvoiceId(allInvoiceInfo.getInvoiceId());
                a.setInvoiceInterfaceTaxCode(EleErrorEnum.E_E_2005.getErrorCode());
                a.setInvoiceInterfaceTaxMessage(EleErrorEnum.E_E_2005.getErrorMsg());
                //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                eleInvoiceInfoService.updateEleInvoiceTaxError(a);
            }

            //插入电子发票中间头表
            EleInvoiceInfo eleInvoiceInfo1 = new EleInvoiceInfo();
            eleInvoiceInfo1.setInvoiceId(allInvoiceInfo.getInvoiceId());
            eleInvoiceInfo1.setDocumentType("ELE_INVOICE_INFO");
            eleInvoiceInfo1.setDocumentCategory("ELE_INVOICE_INFO");
            //流水号
            eleInvoiceInfo1.setSerialNum(swno);
            eleInvoiceInfo1.setSaleTax(saleTax);
            eleInvoiceInfo1.setCustName(allInvoiceInfo.getObjectName());
            eleInvoiceInfo1.setCustTaxNo(allInvoiceInfo.getTaxRegistryNum());
            eleInvoiceInfo1.setCustAddr(allInvoiceInfo.getInvoiceObjectAddressPhone());
            eleInvoiceInfo1.setCustTelephone(custTelephone);
            eleInvoiceInfo1.setCustPhone("");
            eleInvoiceInfo1.setCustEmail(email);
            eleInvoiceInfo1.setCustBankAccount(allInvoiceInfo.getInvoiceObjectBankAccount());
            eleInvoiceInfo1.setCustType(custType);
            eleInvoiceInfo1.setEleInvoiceMemo(allInvoiceInfo.getInvoiceMemo());
            eleInvoiceInfo1.setEleInvoiceType("3");
            eleInvoiceInfo1.setIssuedTime(new Date());
            eleInvoiceInfo1.setCancelNum("");
            //开票类型 1代表开正票
            eleInvoiceInfo1.setBillType("1");
            //默认0  代表正常冲红，电子发票
            eleInvoiceInfo1.setSpecialRedFlag("0");
            //正票正常开具
            eleInvoiceInfo1.setOperationCode("10");
            //用户名称
            eleInvoiceInfo1.setIssuer(allInvoiceInfo.getIssuer());
            //同开票人
            eleInvoiceInfo1.setReviewer(allInvoiceInfo.getReviewer());
            //原发票代码 红冲使用，这里是空
            eleInvoiceInfo1.setFormerInvoiceCode("");
            //原发票代码 红冲使用，这里是空
            eleInvoiceInfo1.setFormerInvoiceNum("");
            //正常票填0
            eleInvoiceInfo1.setInvoiceReverseDesc("0");
            eleInvoiceInfo1.setEleInvoiceStatusCode("NEW");
            acrEleInvoiceHd = eleInvoiceInfoService.insertData(iRequest, eleInvoiceInfo1);

            /**
             * 插入电子发票明细表
             */
            List<EleInvoiceDetail> acrInvoiceLns = acrInvoiceLnMapper.selectAllInvoiceDetail(hd.getInvoiceHdId());
            for (int i = 0; i < acrInvoiceLns.size(); i++) {
                //获取合同ID和合同编号
                List<Map> list = acrEleInvoiceHdMapper.getContractDetail(acrInvoiceLns.get(i).getCashflowId());
                /**
                 * 从 ALL_INVOICE_DETAIL
                 * 获取每一个行项目的税收分类编码
                 *
                 */
                AcrEleInvoiceHd invoiceHd = new AcrEleInvoiceHd();
                invoiceHd.setCashflowId(acrInvoiceLns.get(i).getCashflowId());
                invoiceHd.setCfType(acrInvoiceLns.get(i).getCfType());
                String code = acrEleInvoiceHdMapper.getTaxClassNum(invoiceHd);

                if ("".equals(code) || code == null) {

                    EleInvoiceInfo a = new EleInvoiceInfo();
                    a.setInvoiceId(allInvoiceInfo.getInvoiceId());
                    a.setInvoiceInterfaceTaxCode(EleErrorEnum.E_E_2006.getErrorCode());
                    a.setInvoiceInterfaceTaxMessage(EleErrorEnum.E_E_2006.getErrorMsg());
                    //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                    eleInvoiceInfoService.updateEleInvoiceTaxError(a);
                    return;
                }

                EleInvoiceDetail eleInvoiceDetail = new EleInvoiceDetail();
                eleInvoiceDetail.setEleInvoiceId(acrEleInvoiceHd.getEleInvoiceHdId());
                eleInvoiceDetail.setInvoiceId(acrInvoiceLns.get(i).getInvoiceHdId());
                //eleInvoiceDetail.setInvoiceLnId(acrInvoiceLns.get(i).getInvoiceLnId());
                eleInvoiceDetail.setContractNo(String.valueOf(list.get(0).get("contractNo")));
                //eleInvoiceDetail.setCashflowId(acrInvoiceLns.get(i).getCashflowId());
                eleInvoiceDetail.setBillNo(String.valueOf(list.get(0).get("contractNumber")));
                eleInvoiceDetail.setBillName(acrInvoiceLns.get(i).getProductName());
                eleInvoiceDetail.setBillCode(code); //税收分类编码
                eleInvoiceDetail.setLineType("0");
                eleInvoiceDetail.setSpec(acrInvoiceLns.get(i).getSpecification());
                //acrEleInvoiceLn.setUnit(acrInvoiceLns.get(i).getUom());
                eleInvoiceDetail.setUnit("台");
                eleInvoiceDetail.setTaxRate(acrInvoiceLns.get(i).getTaxTypeRate());
                eleInvoiceDetail.setTaxRquantityate(acrInvoiceLns.get(i).getQuantity());
                eleInvoiceDetail.setTaxPrice(acrInvoiceLns.get(i).getPrice());
                eleInvoiceDetail.setTotalAmount(acrInvoiceLns.get(i).getTotalAmount());
                eleInvoiceDetail.setYhzcbs("0");
                eleInvoiceDetail.setYhzcnr("");
                eleInvoiceDetail.setLslbs(null);
                eleInvoiceDetail.setZxbm("");
                eleInvoiceDetail.setKce(null);
                eleInvoiceDetailService.insertData(iRequest, eleInvoiceDetail);
            }
        }
        return responseData;
    }
}
