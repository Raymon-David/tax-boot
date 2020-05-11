package com.foryou.tax.api.excel;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author ：Raymon
 * @date ：Created in 2020/5/11
 * @description: 导出excel
 */
public class ExportExcel {

    public static void getExcel(HttpServletResponse response, List list, String filename) throws Exception{

        ExcelWriter excelWriter = ExcelUtil.getWriter(true);
        /**
         *一次性写入内存，实用默认样式
         */
        excelWriter.write(list);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        excelWriter.flush(response.getOutputStream());

        /**
         *关闭excelWriter，释放内存
         */
        excelWriter.close();

    }
}
