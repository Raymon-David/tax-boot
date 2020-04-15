package com.foryou.tax.controller.weekly;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/15
 * @description : 税务周报 controller
 */
@RestController
public class WeeklyController {

    @RequestMapping(value = "/paperInvoice/importFile", method = {RequestMethod.POST})
    public void paperInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="paperUploadFile") CommonsMultipartFile file) {
        System.out.println("paper");
    }

    @RequestMapping(value = "/eleInvoice/importFile", method = {RequestMethod.POST})
    public void eleInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="eleUploadFile") CommonsMultipartFile file) {
        System.out.println("ele");
    }
}
