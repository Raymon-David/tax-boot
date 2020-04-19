package com.foryou.tax.controller.weekly;

import com.foryou.tax.process.weekly.WeeklyProcess;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/15
 * @description : 税务周报 controller
 */
@RestController
public class WeeklyController {

    @Autowired
    private WeeklyProcess weeklyProcess;

    @RequestMapping(value = "/paperInvoice/importFile", method = {RequestMethod.POST})
    public void paperInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="paperFile") MultipartFile multipartfile) {

        LoggerUtils.debug(getClass(), "paperFile is: " + multipartfile);
        weeklyProcess.paperInvoiceImport(request, response, multipartfile);
    }

    @RequestMapping(value = "/eleInvoice/importFile", method = {RequestMethod.POST})
    public void eleInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="eleUploadFile") MultipartFile multipartfile) {

        LoggerUtils.debug(getClass(), "eleUploadFile is: " + multipartfile);
        weeklyProcess.eleInvoiceImport(request, response, multipartfile);
    }

}
