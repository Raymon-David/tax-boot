package com.foryou.tax.controller.weekly.writeoffinfo;


import com.foryou.tax.process.weekly.writeoffinfo.WriteOffInfoProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
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
     * 导入系统
     */
    @RequestMapping(value = "/writeOffInfo/import")
    public void writeOffInfoImport(HttpServletRequest request, HttpServletResponse response){

        writeOffInfoProcess.writeOffInfoImport(request, response);
    }

    /**
     * 从缓存中获取一周核销的数据
     */
    @RequestMapping(value = "/writeOffInfo/queryWeekly", method = RequestMethod.POST)
    public void writeOffInfoQueryWeekly(HttpServletRequest request, HttpServletResponse response){
        writeOffInfoProcess.writeOffInfoQueryWeekly(request, response);
    }

}

