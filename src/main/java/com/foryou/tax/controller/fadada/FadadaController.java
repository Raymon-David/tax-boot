package com.foryou.tax.controller.fadada;

import com.foryou.tax.process.fadada.FadadaProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @author ：Raymon
 * @date ：Created in 2020/10/19
 * @description: 法大大相关
 */
@RestController
public class FadadaController {

    @Autowired
    private FadadaProcess fadadaProcess;

    /**
     * 批量下载电子合同
     * @param request
     * @param response
     */
    @RequestMapping(value = "/contract/batchDownload", method = {RequestMethod.POST})
    public void contractBatchDownload(@RequestParam ArrayList contractList, HttpServletRequest request, HttpServletResponse response) {

        fadadaProcess.contractBatchDownload(contractList, request, response);
    }
}
