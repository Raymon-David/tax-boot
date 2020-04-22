package com.foryou.tax.controller.weekly.createInvoiceInfo;

import com.foryou.tax.process.allinvoice.AllInvoiceProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/22
 * @description: 从DCFL导入发票创建的数据
 */
public class DcflCreateInvoiceController {

    @Autowired
    private AllInvoiceProcess allInvoiceProcess;

    /**
     * 导入
     */
    @RequestMapping(value = "/dcflCreateInvoice/import")
    public void dcflCreateInvoiceImport(HttpServletRequest request, HttpServletResponse response){

        allInvoiceProcess.dcflCreateInvoiceImport(request, response);
    }
}
