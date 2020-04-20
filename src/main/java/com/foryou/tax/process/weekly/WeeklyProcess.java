package com.foryou.tax.process.weekly;

import cn.hutool.core.convert.Convert;
import com.foryou.tax.api.bean.SuccessBean;
import com.foryou.tax.api.bean.success.SuccessDesc;
import com.foryou.tax.api.bean.success.SuccessInfo;
import com.foryou.tax.api.excel.ImportExcel;
import com.foryou.tax.pojo.weekly.DcflEleInvoiceImportTemp;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.weekly.DcflEleInvoiceImportTempService;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
        try {
            if(!multipartfile.isEmpty()){
                File file = ImportExcel.multipartToFile(multipartfile);

                /**
                 * excel处理
                 */
                List<Map<String, Object>> list = ImportExcel.importExcel(file);
                LoggerUtils.debug(getClass(), "paperFile list is: " + list);

                SuccessBean successBean = new SuccessBean();
                SuccessInfo successInfo = new SuccessInfo();
                SuccessDesc successDesc = new SuccessDesc();
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

    public void eleInvoiceImport(HttpServletRequest request, HttpServletResponse response, MultipartFile multipartfile) {
        try {
            if(!multipartfile.isEmpty()){
                File file = ImportExcel.multipartToFile(multipartfile);

                /**
                 * excel处理
                 */
                List<Map<String, Object>> list = ImportExcel.importExcel(file);
                LoggerUtils.debug(getClass(), "eleUploadFile list is: " + list);

                for (int i = 0; i < list.size(); i++) {
                    DcflEleInvoiceImportTemp dcflEleInvoiceImportTemp = new DcflEleInvoiceImportTemp();
                    dcflEleInvoiceImportTemp.setSerialNum(Convert.toStr(list.get(i).get("0")));
                    dcflEleInvoiceImportTemp.setBillType(Convert.toStr(list.get(i).get("1")));
                    dcflEleInvoiceImportTemp.setInvoiceType(Convert.toStr(list.get(i).get("2")));
                    dcflEleInvoiceImportTemp.setInvoiceCode(Convert.toStr(list.get(i).get("3")));
                    dcflEleInvoiceImportTemp.setInvoiceNumber(Convert.toStr(list.get(i).get("4")));
                    dcflEleInvoiceImportTemp.setBillMachineNum(Convert.toStr(list.get(i).get("5")));
                    dcflEleInvoiceImportTemp.setInvoiceTitle(Convert.toStr(list.get(i).get("6")));
                    dcflEleInvoiceImportTemp.setTaxRegistryNum(Convert.toStr(list.get(i).get("7")));
                    dcflEleInvoiceImportTemp.setInvoiceObjectAddressPhone(Convert.toStr(list.get(i).get("8")));
                    dcflEleInvoiceImportTemp.setInvoiceObjectBankAccount(Convert.toStr(list.get(i).get("9")));
                    dcflEleInvoiceImportTemp.setIssuedTime(Convert.toDate(list.get(i).get("10")));
                    dcflEleInvoiceImportTemp.setIssuedMonth(Convert.toStr(list.get(i).get("11")));
                    dcflEleInvoiceImportTemp.setTotalAmount(Convert.toBigDecimal(list.get(i).get("12")));
                    dcflEleInvoiceImportTemp.setTaxNetAmount(Convert.toBigDecimal(list.get(i).get("13")));
                    dcflEleInvoiceImportTemp.setTaxRate(Convert.toBigDecimal(list.get(i).get("14")));
                    dcflEleInvoiceImportTemp.setTaxAmount(Convert.toBigDecimal(list.get(i).get("15")));
                    dcflEleInvoiceImportTemp.setProductName(Convert.toStr(list.get(i).get("16")));
                    dcflEleInvoiceImportTemp.setInvoiceMemo(Convert.toStr(list.get(i).get("17")));
                    dcflEleInvoiceImportTemp.setIssuer(Convert.toStr(list.get(i).get("18")));
                    dcflEleInvoiceImportTemp.setAccountPayee(Convert.toStr(list.get(i).get("19")));
                    dcflEleInvoiceImportTemp.setReviewer(Convert.toStr(list.get(i).get("20")));
                    dcflEleInvoiceImportTemp.setInvoiceInvalidFlag(Convert.toStr(list.get(i).get("21")));

                    LoggerUtils.debug(getClass(), "DcflEleInvoiceImportTemp is: " + dcflEleInvoiceImportTemp);
                    dcflEleInvoiceImportTempService.insetData(dcflEleInvoiceImportTemp);
                }

                SuccessBean successBean = new SuccessBean();
                SuccessInfo successInfo = new SuccessInfo();
                SuccessDesc successDesc = new SuccessDesc();
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
