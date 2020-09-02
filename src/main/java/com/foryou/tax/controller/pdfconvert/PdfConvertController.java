package com.foryou.tax.controller.pdfconvert;

import com.foryou.tax.process.pdfconvert.PdfConvertProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;

/**
 * @author ：Raymon
 * @date ：Created in 2020/7/15
 * @description : 利用libreoffice文档转换
 */
@RestController
public class PdfConvertController {

    @Autowired
    PdfConvertProcess pdfConvertProcess;

    /**
     * 文档转换
     */
    @RequestMapping(value ="/pdfConvert" ,method = RequestMethod.POST)
    public void pdfConvert(HttpServletRequest request, HttpServletResponse response){
        pdfConvertProcess.pdfConvert(request, response);
    }
}
