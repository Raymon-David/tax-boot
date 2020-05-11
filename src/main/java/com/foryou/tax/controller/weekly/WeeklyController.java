package com.foryou.tax.controller.weekly;

import com.foryou.tax.process.weekly.WeeklyProcess;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    /**
     * 纸票导入
     * @param request
     * @param response
     * @param multipartfile
     */
    @RequestMapping(value = "/paperInvoice/importFile", method = {RequestMethod.POST})
    public void paperInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="paperFile") MultipartFile multipartfile) {

        LoggerUtils.debug(getClass(), "paperFile is: " + multipartfile);
        weeklyProcess.paperInvoiceImport(request, response, multipartfile);
    }

    /**
     * 电票导入
     * @param request
     * @param response
     * @param multipartfile
     */
    @RequestMapping(value = "/eleInvoice/importFile", method = {RequestMethod.POST})
    public void eleInvoiceImport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="eleUploadFile") MultipartFile multipartfile) {

        LoggerUtils.debug(getClass(), "eleUploadFile is: " + multipartfile);
        weeklyProcess.eleInvoiceImport(request, response, multipartfile);
    }

    /**
     * 金税系统的开票数据和融资资料系统的开票数据对比
     * @param request
     * @param response
     */
    @RequestMapping(value = "/weekly/invoiceMerge", method = {RequestMethod.POST})
    public void weeklyInvoiceMerge(HttpServletRequest request, HttpServletResponse response){

        weeklyProcess.weeklyInvoiceMerge(request, response);
    }

    /**
     * 生成周报
     */
    @RequestMapping(value = "/weekly/createInvoiceExcel", method = {RequestMethod.GET})
    public void createInvoiceExcel(HttpServletRequest request, HttpServletResponse response){

        weeklyProcess.createInvoiceExcel(request, response);
    }


}
