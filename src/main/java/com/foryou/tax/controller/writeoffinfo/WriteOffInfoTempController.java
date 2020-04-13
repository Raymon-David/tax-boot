package com.foryou.tax.controller.writeoffinfo;


import com.foryou.tax.process.writeoffinfo.WriteOffInfoProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 核销事务查询临时表 前端控制器
 * </p>
 *
 * @author raymon
 * @since 2020-04-13
 */
@RestController
public class WriteOffInfoTempController {

    @Autowired
    private WriteOffInfoProcess writeOffInfoProcess;

    /**
     * 发票总表导入系统
     */
    @RequestMapping(value = "/writeOffInfo/import")
    public void writeOffInfoImport(HttpServletRequest request, HttpServletResponse response){

        writeOffInfoProcess.writeOffInfoImport(request, response);
    }

}

