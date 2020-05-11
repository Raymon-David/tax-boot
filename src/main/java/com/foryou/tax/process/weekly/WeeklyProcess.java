package com.foryou.tax.process.weekly;

import cn.hutool.core.convert.Convert;
import com.foryou.tax.api.bean.SuccessBean;
import com.foryou.tax.api.bean.success.SuccessDesc;
import com.foryou.tax.api.bean.success.SuccessInfo;
import com.foryou.tax.api.constant.InvoiceMergeErrorEnum;
import com.foryou.tax.api.excel.ExportExcel;
import com.foryou.tax.api.excel.ImportExcel;
import com.foryou.tax.pojo.weekly.DcflEleInvoiceImportTemp;
import com.foryou.tax.pojo.weekly.DcflPaperInvoiceImportTemp;
import com.foryou.tax.pojo.weekly.mergeinvoice.DcflMergeInvoiceResult;
import com.foryou.tax.pojo.weekly.mergeinvoice.DcflQueryInvoiceV;
import com.foryou.tax.pojo.weekly.mergeinvoice.JinshuiImportInvoiceV;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.weekly.DcflEleInvoiceImportTempService;
import com.foryou.tax.service.weekly.mergeinvoice.DcflMergeInvoiceResultService;
import com.foryou.tax.service.weekly.mergeinvoice.DcflQueryInvoiceVService;
import com.foryou.tax.service.weekly.mergeinvoice.JinshuiImportInvoiceVService;
import com.foryou.tax.util.DateUtil;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/19
 * @description: 税务周报 process
 */
@Service
public class WeeklyProcess extends BaseProcess {

    @Autowired
    private DcflEleInvoiceImportTempService dcflEleInvoiceImportTempService;

    @Autowired
    private JinshuiImportInvoiceVService jinshuiImportInvoiceVService;

    @Autowired
    private DcflQueryInvoiceVService dcflQueryInvoiceVService;

    @Autowired
    private DcflMergeInvoiceResultService dcflMergeInvoiceResultService;

    public void paperInvoiceImport(HttpServletRequest request, HttpServletResponse response, MultipartFile multipartfile) {

        SuccessBean successBean = new SuccessBean();
        SuccessInfo successInfo = new SuccessInfo();
        SuccessDesc successDesc = new SuccessDesc();

        try {
            if(!multipartfile.isEmpty()){
                File file = ImportExcel.multipartToFile(multipartfile);

                /**
                 * 先备份表，然后删除表里的数据，再插入新数据
                 */
                LoggerUtils.debug(getClass(), "DCFL_PAPER_INVOICE_IMPORT_TEMP 备份开始");
                String newTableName = "DCFL_PAPER_INVOICE_IMPORT_TEMP_" + DateUtil.parseDate(new Date());
                dcflEleInvoiceImportTempService.backUpPaperData(newTableName);
                LoggerUtils.debug(getClass(),"DCFL_PAPER_INVOICE_IMPORT_TEMP 备份结束");

                LoggerUtils.debug(getClass(), "DCFL_PAPER_INVOICE_IMPORT_TEMP 删除开始");
                dcflEleInvoiceImportTempService.deletePaperData();
                LoggerUtils.debug(getClass(), "DCFL_PAPER_INVOICE_IMPORT_TEMP 删除结束");

                /**
                 * excel处理
                 */
                List<Map<String, Object>> list = ImportExcel.importExcel(file);
                LoggerUtils.debug(getClass(), "paperFile list is: " + list);

                for (Map<String, Object> stringObjectMap : list) {
                    DcflPaperInvoiceImportTemp dcflPaperInvoiceImportTemp = new DcflPaperInvoiceImportTemp();
                    dcflPaperInvoiceImportTemp.setBillType(Convert.toStr(stringObjectMap.get("0")));
                    dcflPaperInvoiceImportTemp.setInvoiceCode(Convert.toStr(stringObjectMap.get("1")));
                    dcflPaperInvoiceImportTemp.setInvoiceNumber(Convert.toStr(stringObjectMap.get("2")));
                    dcflPaperInvoiceImportTemp.setBillMachineNum(Convert.toStr(stringObjectMap.get("3")));
                    dcflPaperInvoiceImportTemp.setInvoiceTitle(Convert.toStr(stringObjectMap.get("4")));
                    dcflPaperInvoiceImportTemp.setTaxRegistryNum(Convert.toStr(stringObjectMap.get("5")));
                    dcflPaperInvoiceImportTemp.setInvoiceObjectAddressPhone(Convert.toStr(stringObjectMap.get("6")));
                    dcflPaperInvoiceImportTemp.setInvoiceObjectBankAccount(Convert.toStr(stringObjectMap.get("7")));
                    String iss = Convert.toStr(stringObjectMap.get("8"));
                    Date is = Convert.toDate(iss);
                    dcflPaperInvoiceImportTemp.setIssuedTime(is);
                    dcflPaperInvoiceImportTemp.setSubmitType(Convert.toStr(stringObjectMap.get("9")));
                    dcflPaperInvoiceImportTemp.setSubmitLog(Convert.toStr(stringObjectMap.get("10")));
                    dcflPaperInvoiceImportTemp.setIssuedMonth(Convert.toStr(stringObjectMap.get("11")));
                    dcflPaperInvoiceImportTemp.setTotalAmount(Convert.toBigDecimal(stringObjectMap.get("12")));
                    /**
                     * 纸质发票的税率包含 %
                     */
                    String rate = Convert.toStr(stringObjectMap.get("13"));
                    BigDecimal bigDecimal = null;
                    if (rate.contains("%")) {
                        String[] newRate = rate.split("%");
                        String r = newRate[0];
                        bigDecimal = Convert.toBigDecimal(new Double(r) / 100);
                    }else {
                        bigDecimal = Convert.toBigDecimal(rate);
                    }
                    dcflPaperInvoiceImportTemp.setTaxRate(bigDecimal);
                    /**
                     * 纸票上的这个字段代表的是不含税金额，在视图中处理，上传不处理
                     */
                    dcflPaperInvoiceImportTemp.setTaxAmount(Convert.toBigDecimal(stringObjectMap.get("14")));
                    dcflPaperInvoiceImportTemp.setProductName(Convert.toStr(stringObjectMap.get("15")));
                    dcflPaperInvoiceImportTemp.setProductTaxItem(Convert.toStr(stringObjectMap.get("16")));
                    dcflPaperInvoiceImportTemp.setInvoiceMemo(Convert.toStr(stringObjectMap.get("17")));
                    dcflPaperInvoiceImportTemp.setIssuer(Convert.toStr(stringObjectMap.get("18")));
                    dcflPaperInvoiceImportTemp.setAccountPayee(Convert.toStr(stringObjectMap.get("19")));
                    dcflPaperInvoiceImportTemp.setPrintType(Convert.toStr(stringObjectMap.get("20")));
                    dcflPaperInvoiceImportTemp.setInvoiceInvalidFlag(Convert.toStr(stringObjectMap.get("21")));
                    dcflPaperInvoiceImportTemp.setListType(Convert.toStr(stringObjectMap.get("22")));
                    dcflPaperInvoiceImportTemp.setRepareType(Convert.toStr(stringObjectMap.get("23")));
                    dcflPaperInvoiceImportTemp.setReviewer(Convert.toStr(stringObjectMap.get("24")));
                    dcflPaperInvoiceImportTemp.setSaleDepartment(Convert.toStr(stringObjectMap.get("25")));
                    dcflPaperInvoiceImportTemp.setInvoiceInvalidDate(Convert.toDate(stringObjectMap.get("26")));

                    LoggerUtils.debug(getClass(), "DcflPaperInvoiceImportTemp is: " + dcflPaperInvoiceImportTemp);
                    dcflEleInvoiceImportTempService.insetPaperData(dcflPaperInvoiceImportTemp);
                }

                successDesc.setCode("200");
                successDesc.setMessage("上传成功！");
                successInfo.setType("success");
                successInfo.setSuccessDesc(successDesc);
                successBean.setSuccess(successInfo);
                writeClientJson(response, successBean, "");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eleInvoiceImport(HttpServletRequest request, HttpServletResponse response, MultipartFile multipartfile) {

        SuccessBean successBean = new SuccessBean();
        SuccessInfo successInfo = new SuccessInfo();
        SuccessDesc successDesc = new SuccessDesc();

        try {
            if(!multipartfile.isEmpty()){
                File file = ImportExcel.multipartToFile(multipartfile);

                /**
                 * 先备份表，然后删除表里的数据，再插入新数据
                 */
                LoggerUtils.debug(getClass(), "DCFL_ELE_INVOICE_IMPORT_TEMP 备份开始");
                String newTableName = "DCFL_ELE_INVOICE_IMPORT_TEMP_" + DateUtil.parseDate(new Date());
                dcflEleInvoiceImportTempService.backUpEleData(newTableName);
                LoggerUtils.debug(getClass(),"DCFL_ELE_INVOICE_IMPORT_TEMP 备份结束");

                LoggerUtils.debug(getClass(), "DCFL_ELE_INVOICE_IMPORT_TEMP 删除开始");
                dcflEleInvoiceImportTempService.deleteEleData();
                LoggerUtils.debug(getClass(), "DCFL_ELE_INVOICE_IMPORT_TEMP 删除结束");

                /**
                 * excel处理
                 */
                List<Map<String, Object>> list = ImportExcel.importExcel(file);
                LoggerUtils.debug(getClass(), "eleUploadFile list is: " + list);

                for (Map<String, Object> stringObjectMap : list) {
                    DcflEleInvoiceImportTemp dcflEleInvoiceImportTemp = new DcflEleInvoiceImportTemp();
                    dcflEleInvoiceImportTemp.setSerialNum(Convert.toStr(stringObjectMap.get("0")));
                    dcflEleInvoiceImportTemp.setBillType(Convert.toStr(stringObjectMap.get("1")));
                    dcflEleInvoiceImportTemp.setInvoiceType(Convert.toStr(stringObjectMap.get("2")));
                    dcflEleInvoiceImportTemp.setInvoiceCode(Convert.toStr(stringObjectMap.get("3")));
                    dcflEleInvoiceImportTemp.setInvoiceNumber(Convert.toStr(stringObjectMap.get("4")));
                    dcflEleInvoiceImportTemp.setBillMachineNum(Convert.toStr(stringObjectMap.get("5")));
                    dcflEleInvoiceImportTemp.setInvoiceTitle(Convert.toStr(stringObjectMap.get("6")));
                    dcflEleInvoiceImportTemp.setTaxRegistryNum(Convert.toStr(stringObjectMap.get("7")));
                    dcflEleInvoiceImportTemp.setInvoiceObjectAddressPhone(Convert.toStr(stringObjectMap.get("8")));
                    dcflEleInvoiceImportTemp.setInvoiceObjectBankAccount(Convert.toStr(stringObjectMap.get("9")));
                    dcflEleInvoiceImportTemp.setIssuedTime(Convert.toDate(stringObjectMap.get("10")));
                    dcflEleInvoiceImportTemp.setIssuedMonth(Convert.toStr(stringObjectMap.get("11")));
                    dcflEleInvoiceImportTemp.setTotalAmount(Convert.toBigDecimal(stringObjectMap.get("12")));
                    dcflEleInvoiceImportTemp.setTaxNetAmount(Convert.toBigDecimal(stringObjectMap.get("13")));
                    dcflEleInvoiceImportTemp.setTaxRate(Convert.toBigDecimal(stringObjectMap.get("14")));
                    dcflEleInvoiceImportTemp.setTaxAmount(Convert.toBigDecimal(stringObjectMap.get("15")));
                    dcflEleInvoiceImportTemp.setProductName(Convert.toStr(stringObjectMap.get("16")));
                    dcflEleInvoiceImportTemp.setInvoiceMemo(Convert.toStr(stringObjectMap.get("17")));
                    dcflEleInvoiceImportTemp.setIssuer(Convert.toStr(stringObjectMap.get("18")));
                    dcflEleInvoiceImportTemp.setAccountPayee(Convert.toStr(stringObjectMap.get("19")));
                    dcflEleInvoiceImportTemp.setReviewer(Convert.toStr(stringObjectMap.get("20")));
                    dcflEleInvoiceImportTemp.setInvoiceInvalidFlag(Convert.toStr(stringObjectMap.get("21")));

                    LoggerUtils.debug(getClass(), "DcflEleInvoiceImportTemp is: " + dcflEleInvoiceImportTemp);
                    dcflEleInvoiceImportTempService.insetEleData(dcflEleInvoiceImportTemp);
                }

                successDesc.setCode("200");
                successDesc.setMessage("上传成功！");
                successInfo.setType("success");
                successInfo.setSuccessDesc(successDesc);
                successBean.setSuccess(successInfo);
                writeClientJson(response, successBean, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void weeklyInvoiceMerge(HttpServletRequest request, HttpServletResponse response) {

        LoggerUtils.debug(getClass(), "Weekly invoice merge start");
        SuccessBean successBean = new SuccessBean();
        SuccessInfo successInfo = new SuccessInfo();
        SuccessDesc successDesc = new SuccessDesc();

        List<JinshuiImportInvoiceV> jinshuiList = jinshuiImportInvoiceVService.queryJinshuiImportData();

        for(int i = 0; i < jinshuiList.size(); i++){
            DcflMergeInvoiceResult dcflMergeInvoiceResult = new DcflMergeInvoiceResult();

            String keyWords = jinshuiList.get(i).getKeywords();
            LoggerUtils.debug(getClass(), "Weekly invoice merge keyWords is " + keyWords);
            dcflMergeInvoiceResult.setKeywords(keyWords);
            dcflMergeInvoiceResult.setInvoiceCode(jinshuiList.get(i).getInvoiceCode());
            dcflMergeInvoiceResult.setInvoiceNumber(jinshuiList.get(i).getInvoiceNumber());

            /**
             * 发票备注
             */
            String invoiceMemo = jinshuiList.get(i).getInvoiceMemo();
            dcflMergeInvoiceResult.setInvoiceMemo(invoiceMemo);

            /**
             * 按照左括号分隔, 二手机的左括号是中文的
             * 合同号
             */
            String[] memo = null;
            if (invoiceMemo.contains("(")) {
                memo = invoiceMemo.split("[(]");
            }else if (invoiceMemo.contains("（")) {
                memo = invoiceMemo.split("[（]");
            }

            assert memo != null;
            String contractNumber = memo[1].substring(0, 11);
            LoggerUtils.debug(getClass(), "Weekly invoice merge contractNumber is " + contractNumber);
            dcflMergeInvoiceResult.setContractNumber(contractNumber);

            DcflQueryInvoiceV dcflQueryInvoiceV = dcflQueryInvoiceVService.dcflQueryInvoiceDataByKeywords(keyWords);
            if (dcflQueryInvoiceV == null) {
                dcflMergeInvoiceResult.setInvoiceMergeResult(InvoiceMergeErrorEnum.IMEE_10007.getErrorMsg());
                dcflMergeInvoiceResult.setInvoiceTitle(jinshuiList.get(i).getInvoiceTitle());
                dcflMergeInvoiceResult.setIssuedTime(jinshuiList.get(i).getIssuedTime());
                dcflMergeInvoiceResult.setIssuedMonth(jinshuiList.get(i).getIssuedMonth());
                dcflMergeInvoiceResult.setTaxRegistryNum(jinshuiList.get(i).getTaxRegistryNum());
                dcflMergeInvoiceResult.setInvoiceObjectAddressPhone(jinshuiList.get(i).getInvoiceObjectAddressPhone());
                dcflMergeInvoiceResult.setInvoiceObjectBankAccount(jinshuiList.get(i).getInvoiceObjectBankAccount());
                dcflMergeInvoiceResult.setTotalAmount(jinshuiList.get(i).getTotalAmount());
                dcflMergeInvoiceResult.setTaxNetAmount(jinshuiList.get(i).getTaxNetAmount());
                dcflMergeInvoiceResult.setTaxAmount(jinshuiList.get(i).getTaxAmount());
                dcflMergeInvoiceResult.setIssuer(jinshuiList.get(i).getIssuer());
                dcflMergeInvoiceResult.setReviewer(jinshuiList.get(i).getReviewer());
                dcflMergeInvoiceResult.setInvoiceInvalidFlag(jinshuiList.get(i).getInvoiceInvalidFlag());
                dcflMergeInvoiceResult.setTaxRate(jinshuiList.get(i).getTaxRate());

                dcflMergeInvoiceResultService.insertData(dcflMergeInvoiceResult);
                continue;
            }

            /**
             * 发票备注上的名称和购货名称merge
             */
            String memoTitle = memo[0].trim();
            if (!memoTitle.equals(jinshuiList.get(i).getInvoiceTitle().trim())) {
                LoggerUtils.debug(getClass(), "memoTitle is: " + memoTitle + " and invoiceTitle is: " + jinshuiList.get(i).getInvoiceTitle());
                dcflMergeInvoiceResult.setInvoiceMergeResult(InvoiceMergeErrorEnum.IMEE_10001.getErrorMsg() + "---memoTitle is: " + memoTitle + " and invoiceTitle is: " + jinshuiList.get(i).getInvoiceTitle());
            }
            dcflMergeInvoiceResult.setInvoiceTitle(jinshuiList.get(i).getInvoiceTitle());

            /**
             * 金税系统与融资租赁系统开票日期merge
             */
            Date jinshuiIssuedDate = Convert.toDate(jinshuiList.get(i).getIssuedTime());
            Date dcflIssuedDate = Convert.toDate(dcflQueryInvoiceV.getIssuedTime().trim());
            if (!jinshuiIssuedDate.equals(dcflIssuedDate)) {
                LoggerUtils.debug(getClass(), "jinshuiIssuedDate is: " + jinshuiIssuedDate + " and dcflIssuedDate is: " + dcflIssuedDate);
                dcflMergeInvoiceResult.setInvoiceMergeResult(InvoiceMergeErrorEnum.IMEE_10002.getErrorMsg() + "---jinshuiIssuedDate is: " + jinshuiIssuedDate + " and dcflIssuedDate is: " + dcflIssuedDate);
            }
            dcflMergeInvoiceResult.setIssuedTime(jinshuiList.get(i).getIssuedTime());
            dcflMergeInvoiceResult.setIssuedMonth(jinshuiList.get(i).getIssuedMonth());

            /**
             * 金税系统与融资租赁系统含税金额merge
             */
            if (!jinshuiList.get(i).getTotalAmount().equals(dcflQueryInvoiceV.getTotalAmount())) {
                LoggerUtils.debug(getClass(), "jinshui totalAmount is: " + jinshuiList.get(i).getTotalAmount() + " and dcfl totalAmount is: " + dcflQueryInvoiceV.getTotalAmount());
                dcflMergeInvoiceResult.setInvoiceMergeResult(InvoiceMergeErrorEnum.IMEE_10003.getErrorMsg() + "---jinshui totalAmount is: " + jinshuiList.get(i).getTotalAmount() + " and dcfl totalAmount is: " + dcflQueryInvoiceV.getTotalAmount());
            }
            dcflMergeInvoiceResult.setTotalAmount(jinshuiList.get(i).getTotalAmount());

            /**
             * 金税系统与融资租赁系统不含税金额merge
             */
            if (!jinshuiList.get(i).getTaxNetAmount().equals(dcflQueryInvoiceV.getTaxNetAmount())) {
                LoggerUtils.debug(getClass(), "jinshui taxNetAmount is: " + jinshuiList.get(i).getTaxNetAmount() + " and dcfl taxNetAmount is: " + dcflQueryInvoiceV.getTaxNetAmount());
                dcflMergeInvoiceResult.setInvoiceMergeResult(InvoiceMergeErrorEnum.IMEE_10004.getErrorMsg() + "---jinshui taxNetAmount is: " + jinshuiList.get(i).getTaxNetAmount() + " and dcfl taxNetAmount is: " + dcflQueryInvoiceV.getTaxNetAmount());
            }
            dcflMergeInvoiceResult.setTaxNetAmount(jinshuiList.get(i).getTaxNetAmount());

            /**
             * 金税系统与融资租赁系统税额merge
             */
            if (!jinshuiList.get(i).getTaxAmount().equals(dcflQueryInvoiceV.getTaxAmount())) {
                LoggerUtils.debug(getClass(), "jinshui taxAmount is: " + jinshuiList.get(i).getTaxAmount() + " and dcfl taxAmount is: " + dcflQueryInvoiceV.getTaxAmount());
                dcflMergeInvoiceResult.setInvoiceMergeResult(InvoiceMergeErrorEnum.IMEE_10005.getErrorMsg() + "---jinshui taxAmount is: " + jinshuiList.get(i).getTaxAmount() + " and dcfl taxAmount is: " + dcflQueryInvoiceV.getTaxAmount());
            }
            dcflMergeInvoiceResult.setTaxAmount(jinshuiList.get(i).getTaxAmount());


            /**
             * 金税系统与融资租赁系统反冲标志merge
             */
            if (!jinshuiList.get(i).getInvoiceInvalidFlag().trim().equals(dcflQueryInvoiceV.getInvoiceInvalidFlag().trim())) {
                LoggerUtils.debug(getClass(), "jinshui invoiceInvalidFlag is: " + jinshuiList.get(i).getInvoiceInvalidFlag() + " and dcfl invoiceInvalidFlag is: " + dcflQueryInvoiceV.getInvoiceInvalidFlag());
                dcflMergeInvoiceResult.setInvoiceMergeResult(InvoiceMergeErrorEnum.IMEE_10006.getErrorMsg() + "---jinshui invoiceInvalidFlag is: " + jinshuiList.get(i).getInvoiceInvalidFlag() + " and dcfl invoiceInvalidFlag is: " + dcflQueryInvoiceV.getInvoiceInvalidFlag());
            }
            dcflMergeInvoiceResult.setInvoiceInvalidFlag(jinshuiList.get(i).getInvoiceInvalidFlag());

            dcflMergeInvoiceResult.setTaxRegistryNum(jinshuiList.get(i).getTaxRegistryNum());
            dcflMergeInvoiceResult.setInvoiceObjectAddressPhone(jinshuiList.get(i).getInvoiceObjectAddressPhone());
            dcflMergeInvoiceResult.setInvoiceObjectBankAccount(jinshuiList.get(i).getInvoiceObjectBankAccount());
            dcflMergeInvoiceResult.setIssuer(jinshuiList.get(i).getIssuer());
            dcflMergeInvoiceResult.setReviewer(jinshuiList.get(i).getReviewer());
            dcflMergeInvoiceResult.setTaxRate(jinshuiList.get(i).getTaxRate());

            dcflMergeInvoiceResultService.insertData(dcflMergeInvoiceResult);
        }
        LoggerUtils.debug(getClass(), "Weekly invoice merge end");

        successDesc.setCode("200");
        successDesc.setMessage("merge成功！");
        successInfo.setType("success");
        successInfo.setSuccessDesc(successDesc);
        successBean.setSuccess(successInfo);
        writeClientJson(response, successBean, "");

    }

    /**
     * 生成周报 excel
     * @param request
     * @param response
     */
    public void exportInvoiceMergeExcel(HttpServletRequest request, HttpServletResponse response) {

        List<DcflMergeInvoiceResult> list = dcflMergeInvoiceResultService.queryMergeResultData();
        String filename = "发票merge周报.xlsx";
        try {
            ExportExcel.getExcel(response, list, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportDcflInvoiceExcel(HttpServletRequest request, HttpServletResponse response) {

        List<DcflQueryInvoiceV> list = dcflQueryInvoiceVService.queryDcflInvoiceData();
        String filename = "一周DCFL销项发票查询中的数据.xlsx";
        try {
            ExportExcel.getExcel(response, list, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportJinshuiInvoiceExcel(HttpServletRequest request, HttpServletResponse response) {

        List<JinshuiImportInvoiceV> list = jinshuiImportInvoiceVService.queryJinshuiImportData();
        String filename = "一周金税纸票、电票导入的数据.xlsx";
        try {
            ExportExcel.getExcel(response, list, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
