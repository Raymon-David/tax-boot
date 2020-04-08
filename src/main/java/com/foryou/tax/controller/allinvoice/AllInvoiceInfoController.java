package com.foryou.tax.controller.allinvoice;


import com.foryou.tax.process.allinvoice.AllInvoiceProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 发票总表 前端控制器
 * </p>
 *
 * @author raymon
 * @since 2020-03-18
 */
@RestController
public class AllInvoiceInfoController {

    @Autowired
    private AllInvoiceProcess allInvoiceProcess;


    /**
     * 发票总表导入系统
     */
    @RequestMapping(value = "/allInvoice/import")
    public void allInvoiceImport(HttpServletRequest request, HttpServletResponse response){

        allInvoiceProcess.allInvoiceImport(request, response);
    }
}

