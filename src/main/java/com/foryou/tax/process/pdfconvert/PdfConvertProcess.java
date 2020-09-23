package com.foryou.tax.process.pdfconvert;

import com.foryou.tax.api.jacob.documentconvertbylibreoffice.ConvertPdfByLibreOffice;
import com.foryou.tax.process.common.BaseProcess;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description : 利用libreoffice文档转换
 */
@Service
public class PdfConvertProcess extends BaseProcess {

    public void pdfConvert(HttpServletRequest request, HttpServletResponse response){
        String documentName = "斗山OneID体系设计方案V1.0.docx";
        ConvertPdfByLibreOffice convertPdfByLibreOffice = new ConvertPdfByLibreOffice();
        Map<String, Object> result = convertPdfByLibreOffice.pdfConvert(documentName);

        writeClientJson(response, result, null);
    }
}
