package com.foryou.tax.controller.classificationcode;


import com.foryou.tax.process.classificationcode.InvoiceTaxClassificationCodeProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 税收分类编码 前端控制器
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
@RestController
public class InvoiceTaxClassificationCodeController {

    @Autowired
    InvoiceTaxClassificationCodeProcess invoiceTaxClassificationCodeProcess;


    /**
     * 税收分类编码导入系统
     */
    @RequestMapping(value = "/invoiceTaxClassificationCode/import")
    public void invoiceTaxClassificationCodeImport(HttpServletRequest request, HttpServletResponse response){

        invoiceTaxClassificationCodeProcess.invoiceTaxClassificationCodeImport(request, response);
    }
}

