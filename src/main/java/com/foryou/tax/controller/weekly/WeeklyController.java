package com.foryou.tax.controller.weekly;

import com.foryou.tax.api.excel.ImportExcel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/15
 * @description : 税务周报 controller
 */
@RestController
public class WeeklyController {



    @RequestMapping(value = "/paperInvoice/importFile", method = {RequestMethod.POST})
    public void paperInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="paperFile") MultipartFile multipartfile) {

        try {
            if(!multipartfile.isEmpty()){
                File file = ImportExcel.multipartToFile(multipartfile);

                /**
                 * excel处理
                 */
                List<Map<String, Object>> list = ImportExcel.importExcel(file);
                System.out.println(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/eleInvoice/importFile", method = {RequestMethod.POST})
    public void eleInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="eleUploadFile") MultipartFile multipartfile) {

        try {
            if(!multipartfile.isEmpty()){
                File file = ImportExcel.multipartToFile(multipartfile);

                /**
                 * excel处理
                 */
                List<Map<String, Object>> list = ImportExcel.importExcel(file);
                System.out.println(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
