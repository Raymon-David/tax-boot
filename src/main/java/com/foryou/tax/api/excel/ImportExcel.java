package com.foryou.tax.api.excel;
import cn.hutool.core.convert.Convert;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description : excel导入通用方法
 */
public class ImportExcel {

    public  static List<Map<String, Object>> importExcel(File excelFile) throws Exception {

        //容器
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();

        try {
            FileInputStream in = new FileInputStream(excelFile); // 文件流
            Workbook workbook = WorkbookFactory.create(in); // 这种方式 Excel2003/2007/2010都是可以处理的

            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            System.out.println("Sheet的数量" + sheetCount);
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet

            //获取总行数
            System.out.println("总行数" + sheet.getLastRowNum());

            // 为跳过第一行目录设置count
            int count = 0;

            for (Row row : sheet) {

                try {
                    // 跳过第一行的目录
                    if(count < 1 ) {
                        count++;
                        continue;
                    }


                    /**
                     * 如果当前行没有数据，跳出循环 这样写会导致第一列为空就默认为这一行没数据
                     */
                   if("".equals(Convert.toStr(row.getCell(0))) || row.getCell(0) == null){
                      continue;
                    }

                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                    System.out.println("总列数：" + columnTotalNum);
                    System.out.println("最大列数：" + row.getLastCellNum());
                    int end = row.getLastCellNum();

                    //容器
                    Map<String, Object> map = new HashMap<String, Object>();

                    for (int i = 0; i < end; i++) {
                        Cell cell = row.getCell(i);
                        if(cell == null) {
                            System.out.print("null" + "\t");
                            continue;
                        }

                        Object obj = getValue(cell);
                        map.put(String.valueOf(i), obj == null ? "" : obj.toString());
                    }
                    li.add(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return li;
        }
    }

    private static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

    /**
     *
     * @param multipart
     * @throws IllegalStateException
     * @throws IOException
     *   MultipartFile 转 File
     */
    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipart.getBytes());
        fos.close();
        return convFile;
    }
}
