package com.foryou.tax.controller.weekly.queryinvoice;


import com.foryou.tax.process.weekly.queryinvoice.DcflQueryInvoiceTempProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 从DCFL销项发票查询导入数据 临时表 前端控制器
 * </p>
 *
 * @author raymon
 * @since 2020-04-28
 */
@RestController
public class DcflQueryInvoiceTempController {

    @Autowired
    private DcflQueryInvoiceTempProcess dcflQueryInvoiceTempProcess;

    @RequestMapping(value = "/dcflQueryInvoiceTempImport", method = RequestMethod.POST)
    public void dcflQueryInvoiceTempImport(HttpServletRequest request, HttpServletResponse response){

        dcflQueryInvoiceTempProcess.dcflQueryInvoiceTempImport(request, response);
    }

}

