package com.foryou.tax.process.weekly;

import cn.hutool.core.convert.Convert;
import com.foryou.tax.api.bean.SuccessBean;
import com.foryou.tax.api.bean.success.SuccessDesc;
import com.foryou.tax.api.bean.success.SuccessInfo;
import com.foryou.tax.api.excel.ImportExcel;
import com.foryou.tax.pojo.weekly.DcflEleInvoiceImportTemp;
import com.foryou.tax.pojo.weekly.DcflPaperInvoiceImportTemp;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.weekly.DcflEleInvoiceImportTempService;
import com.foryou.tax.util.DateUtil;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
                    dcflPaperInvoiceImportTemp.setIssuedTime(Convert.toDate(stringObjectMap.get("8")));
                    dcflPaperInvoiceImportTemp.setSubmitType(Convert.toStr(stringObjectMap.get("9")));
                    dcflPaperInvoiceImportTemp.setSubmitLog(Convert.toStr(stringObjectMap.get("10")));
                    dcflPaperInvoiceImportTemp.setIssuedMonth(Convert.toStr(stringObjectMap.get("11")));
                    dcflPaperInvoiceImportTemp.setTaxAmount(Convert.toBigDecimal(stringObjectMap.get("12")));
                    dcflPaperInvoiceImportTemp.setTaxRate(Convert.toBigDecimal(stringObjectMap.get("13")));
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
}
