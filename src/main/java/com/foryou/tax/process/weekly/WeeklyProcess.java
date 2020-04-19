package com.foryou.tax.process.weekly;

import com.foryou.tax.api.bean.SuccessBean;
import com.foryou.tax.api.bean.success.SuccessDesc;
import com.foryou.tax.api.bean.success.SuccessInfo;
import com.foryou.tax.api.excel.ImportExcel;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.util.LoggerUtils;
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
