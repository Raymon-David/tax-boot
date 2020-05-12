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
     * 导出merge周报excel
     */
    @RequestMapping(value = "/weekly/exportInvoiceMergeExcel", method = {RequestMethod.POST})
    public void exportInvoiceMergeExcel(HttpServletRequest request, HttpServletResponse response){

        weeklyProcess.exportInvoiceMergeExcel(request, response);
    }

    /**
     * 导出一周DCFL销项发票查询中的数据
     */
    @RequestMapping(value = "/weekly/exportDcflInvoiceExcel", method = {RequestMethod.POST})
    public void exportDcflInvoiceExcel(HttpServletRequest request, HttpServletResponse response){

        weeklyProcess.exportDcflInvoiceExcel(request, response);
    }


    /**
     * 导出一周金税纸票、电票导入的数据
     */
    @RequestMapping(value = "/weekly/exportJinshuiInvoiceExcel", method = {RequestMethod.POST})
    public void exportJinshuiInvoiceExcel(HttpServletRequest request, HttpServletResponse response){

        weeklyProcess.exportJinshuiInvoiceExcel(request, response);
    }

    /**
     *导出核销的一周报表
     */
    @RequestMapping(value = "/weekly/exportWriteOffExcel", method = {RequestMethod.POST})
    public void exportWriteOffExcel(HttpServletRequest request, HttpServletResponse response) {

        weeklyProcess.exportWriteOffExcel(request, response);
    }

    /**
     *导出发票创建的一周报表
     */
    @RequestMapping(value = "/weekly/exportCreateInvoiceExcel", method = {RequestMethod.POST})
    public void exportCreateInvoiceExcel(HttpServletRequest request, HttpServletResponse response) {

        weeklyProcess.exportCreateInvoiceExcel(request, response);
    }

    /**
     *导出All 一周报表
     */
    @RequestMapping(value = "/weekly/exportAllExcel", method = {RequestMethod.POST})
    public void exportAllExcel(HttpServletRequest request, HttpServletResponse response) {

        weeklyProcess.exportAllExcel(request, response);
    }


}
