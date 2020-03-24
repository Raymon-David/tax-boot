package com.foryou.tax.process.eleinvoice;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.api.bean.ErrorBean;
import com.foryou.tax.api.bean.error.ErrorDesc;
import com.foryou.tax.api.bean.error.ErrorInfo;
import com.foryou.tax.api.constant.EleErrorEnum;
import com.foryou.tax.api.constant.StatusCodeEnum;
import com.foryou.tax.pojo.allinvoice.AllInvoiceDetail;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;
import com.foryou.tax.pojo.companies.FyCompanies;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceDetail;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import com.foryou.tax.pojo.invoiceobject.InvoiceObjectInfo;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.allinvoice.AllInvoiceDetatilService;
import com.foryou.tax.service.companies.FyCompaniesService;
import com.foryou.tax.service.eleinvoice.EleInvoiceDetailService;
import com.foryou.tax.service.eleinvoice.EleInvoiceInfoService;
import com.foryou.tax.service.invoiceobject.InvoiceObjectInfoService;
import com.foryou.tax.util.eleinvoice.EleInvoiceSubmitXmlUtil;
import com.foryou.tax.util.eleinvoice.GetMarginXmlUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
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

    @Autowired
    private InvoiceObjectInfoService invoiceObjectInfoService;

    @Autowired
    private FyCompaniesService fyCompaniesService;

    @Autowired
    private AllInvoiceDetatilService allInvoiceDetatilService;

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

                    EleInvoiceInfo eleInvoices = eleInvoiceInfoService.getEleInvoiceInfo(eleInvoiceInfo);

                    /**
                     * 如果出现"请维护对应的税收分类编码"这种错误
                     * 先删掉 ele_invoice_info , ele_invoice_detail 中这条数据
                     * 因为 税收编码跟随着每一条现金流
                     */
                    if(eleInvoices != null){
                        if(EleErrorEnum.E_E_2006.getErrorCode().equals(eleInvoices.getInvoiceInterfaceTaxCode())){
                            long eleId = eleInvoices.getEleInvoiceId();
                            eleInvoiceInfoService.deleteData(eleInvoices);
                            eleInvoiceDetailService.deleteData(eleInvoices);
                        }
                    }

                    /**
                     * 删完之后再重新查询一下
                     */
                    eleInvoices = eleInvoiceInfoService.getEleInvoiceInfo(eleInvoiceInfo);

                    if (eleInvoices == null) {
                        data = insertEleInvoiceInfo(request, response, allInvoiceDataList.get(i));

                        if (data != null){
                            //调用发送接口报文封装类
                            String xml = EleInvoiceSubmitXmlUtil.eleInvoiceSubmitXml(allInvoiceDataList.get(i), eleInvoiceInfoService, eleInvoiceDetailService);

                            //调用发送接口工具类
                            JSONObject jsonObject = EleInvoiceSubmitXmlUtil.postData(xml);
                            if (0 == jsonObject.size()) {
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
                                /**
                                 * 更新电子发票状态，表示已经传入金税接口
                                 * 金税接口状态（PENDING-暂挂 / TRANSFERRED-已传金税 / BACK-金税已回写）
                                 */

                                eleInvoiceInfoService.updateEleInvoiceInterfaceStatus(allInvoiceDataList.get(i).getInvoiceId());

                                //开票成功结束后，更新发票余量
                                int num = acrEleNumber - allInvoiceDataList.size();

                                /**
                                 * 查询 FY_COMPANIES 表
                                 */
                                FyCompanies fyCompanies = new FyCompanies();
                                fyCompanies.setCompanyId(allInvoiceDataList.get(i).getCompanyId());
                                fyCompanies.setCompanyEleMargin(num);
                                fyCompaniesService.updateCompanyEleMargin(fyCompanies);
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
        EleInvoiceInfo eleInvoiceHds = eleInvoiceInfoService.getEleInvoiceInfo(eleInvoiceInfo);

        if (eleInvoiceHds != null) {
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
             * 规则 EI_年月日_companycode_100000001
             */
            FyCompanies fyCompanies = new FyCompanies();
            fyCompanies.setCompanyId(allInvoiceInfo.getCompanyId());
            String serialNum = eleInvoiceInfoService.getSerialNum(fyCompanies);
            /**
             * 购货方企业类型
             * 查询开票对象表
             */
            if(allInvoiceInfo.getObjectCode() == null){

                errorDesc.setMessage(EleErrorEnum.E_E_2007.getErrorMsg());
                errorDesc.setCode(EleErrorEnum.E_E_2007.getErrorCode());
                errorInfo.setErrDesc(errorDesc);
                errorInfo.setType("error");
                errorBean.setError(errorInfo);
                writeClientJson(response, errorBean, null);
            }else{

                InvoiceObjectInfo invoiceObjectInfo1 = new InvoiceObjectInfo();
                invoiceObjectInfo1.setObjectCode(allInvoiceInfo.getObjectCode());
                InvoiceObjectInfo invoiceObjectInfo = invoiceObjectInfoService.getInvoiceObjectInfo(invoiceObjectInfo1);

                /**
                 * 01：企业
                 * 02：机关执业单位
                 * 03：个人
                 * 04：其他
                 */
                String custType = invoiceObjectInfo.getObjectTypeCode();
                if("".equals(custType) || custType == null){
                    EleInvoiceInfo a = new EleInvoiceInfo();
                    a.setInvoiceId(allInvoiceInfo.getInvoiceId());
                    a.setInvoiceInterfaceTaxCode(EleErrorEnum.E_E_2008.getErrorCode());
                    a.setInvoiceInterfaceTaxMessage(EleErrorEnum.E_E_2008.getErrorMsg());
                    //更新电子发票状态，表示已经传入金税接口，并更新错误代码和错误提示
                    eleInvoiceInfoService.updateEleInvoiceTaxError(a);
                }

                /**
                 * 获取电话号码
                 * 查询开票对象表
                 */
                String custTelephone = invoiceObjectInfo.getInvoicePhone();
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
                String email = invoiceObjectInfo.getInvoiceEmail();

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
                eleInvoiceInfo1.setSerialNum(serialNum);
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
                eleInvoiceInfo1.setEleInvoiceStatusCode(StatusCodeEnum.ES_2001.getStatusCode());
                eleInvoiceInfo1.setEleInvoiceStatusName(StatusCodeEnum.ES_2001.getStatusName());
                eleInvoiceInfo1.setInvoiceInterfaceStatusCode(StatusCodeEnum.EIS_3001.getStatusCode());
                eleInvoiceInfo1.setInvoiceInterfaceStatusName(StatusCodeEnum.EIS_3001.getStatusName());
                /**
                 * request 里有用户相关信息
                 */
                eleInvoiceInfoService.insertData(request, eleInvoiceInfo1);

                /**
                 * 插入电子发票明细表
                 */
                List<AllInvoiceDetail> allInvoiceDetails = allInvoiceDetatilService.getAllInvoiceDetailInfo(allInvoiceInfo.getInvoiceId());
                for (int i = 0; i < allInvoiceDetails.size(); i++) {
                    /**
                     * 获取合同编号
                     * 插入 ELE_INVOICE_DETAIL 中的 CONTRACT_NO 和 BILL_NO
                     */
                    List<Map> list = acrEleInvoiceHdMapper.getContractDetail(allInvoiceDetails.get(i).getCashflowId());

                    /**
                     * 通过 ALL_INVOICE_DETAIL 中的 CASHFLOW_ID 和 CASHFLOW_ITEM_CODE
                     * ALL_INVOICE_INFO 中的 COMMODITY_CODE
                     * 从 INVOICE_TAX_CLASSIFICATION_CODE
                     * 获取每一个行项目的税收分类编码
                     *
                     */
                    String code = eleInvoiceDetailService.getTaxClassNum(allInvoiceDetails.get(i));

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
                    eleInvoiceDetail.setEleInvoiceId(eleInvoiceInfo1.getEleInvoiceId());
                    eleInvoiceDetail.setInvoiceId(allInvoiceDetails.get(i).getInvoiceId());
                    eleInvoiceDetail.setContractNo(String.valueOf(list.get(0).get("contractNumber")));
                    eleInvoiceDetail.setBillNo(String.valueOf(list.get(0).get("contractNumber")));
                    eleInvoiceDetail.setBillName(allInvoiceDetails.get(i).getProductName());
                    //税收分类编码
                    eleInvoiceDetail.setBillCode(code);
                    eleInvoiceDetail.setLineType("0");
                    eleInvoiceDetail.setSpec(allInvoiceDetails.get(i).getSpec());
                    eleInvoiceDetail.setUnit("台");
                    eleInvoiceDetail.setTaxRate(BigDecimal.valueOf(allInvoiceDetails.get(i).getTaxRate()));
                    eleInvoiceDetail.setTaxQuantity(allInvoiceDetails.get(i).getTaxQuantity());
                    eleInvoiceDetail.setTaxPrice(allInvoiceDetails.get(i).getTaxPrice());
                    eleInvoiceDetail.setTotalAmount(allInvoiceDetails.get(i).getTotalAmount());
                    eleInvoiceDetail.setYhzcbs("0");
                    eleInvoiceDetail.setYhzcnr("");
                    eleInvoiceDetail.setLslbs(null);
                    eleInvoiceDetail.setZxbm("");
                    eleInvoiceDetail.setKce(null);
                    eleInvoiceDetailService.insertData(request, eleInvoiceDetail);
                }

            }
        }
    }
}
