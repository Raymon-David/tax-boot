package com.foryou.tax.api.excel;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.foryou.tax.util.LoggerUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/5/11
 * @description: 导出excel
 */
public class ExportExcel {

    /**
     * 导出普通excel
     * @param response
     * @param list
     * @param filename
     * @throws Exception
     */
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

    /**
     * 导出大数据量的excel
     * @param response
     * @param list
     * @param filename
     * @throws Exception
     */
    public static void getBigExcel(HttpServletResponse response, List list, String filename) throws Exception{

        ExcelWriter excelWriter = ExcelUtil.getBigWriter();
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

    /**
     * 导出多个sheet的excel
     */
    public static void getManySheetExcel(HttpServletResponse response, List<Map<String, Object>> list, String filename) throws Exception{

        ExcelWriter excelWriter = ExcelUtil.getWriter(true);

        /**
         *一次性写入内存，实用默认样式
         */
        for(int i = 0; i < list.size(); i++){
            for(Map.Entry<String, Object> entry : list.get(i).entrySet()){
                String sheetName = entry.getKey();
                LoggerUtils.debug(ExportExcel.class, "sheetName is: " + sheetName);
                excelWriter.setSheet(sheetName);
                excelWriter.write((Iterable<?>) entry.getValue());
            }

        }
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
