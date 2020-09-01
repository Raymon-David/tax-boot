package com.foryou.tax.api.documentconvertbylibreoffice;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 该方法适用于Linux环境
 * 在Linux安装docker，docker安装libreoffice容器，使用该容器进行文档转换
 */
public class TestConvert {
    /**
     * 前台
     * //返回ajax调用的参数
     * success: function(data){
     *   var getdata = eval(data);
     * var curUrls=getdata.finallUrl;
     * var fileName=getdata.filname;
     * var pdfname=getdata.pdfname;
     * var obj={
     * text:fileName
     * };
     * var url="${ctxStatic}/pdfjs/web/viewer.html?file=" + curUrls;
     * parent.parent.addTabInfo(obj,url);//可直接转到连接 local.href="${ctxStatic}/pdfjs/web/viewer.html?file=" + curUrls;
     *
     *   }
     */
    public static void main(String[] args) {
        String documentIp = null;
        String documentPath = null;
        String documentTargetPath = null;
        String documentName = "(approved)斗山20年体检服务指南.docx";

        //定义map 绑定传给前台参数
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        try {
            Properties properties = new Properties();
            InputStream is = PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(is);
            documentIp = properties.getProperty("document_ip");
            documentPath = properties.getProperty("document_path");//文件路径
            documentTargetPath = properties.getProperty("document_target_path"); //输出pdf文件路径
        }catch (Exception e){
            e.printStackTrace();
        }

        String osName = System.getProperty("os.name");
        long curtimeName = System.currentTimeMillis();
        String pdfName =curtimeName+".pdf";//用当前毫秒值定义的文件名字
        /**
         * 利用cmd命令 使用libreoffice转换pdf文件
         *
         * docker exec -it LibreOfficeOnline /bin/soffice –headless  –invisible –norestore –nodefault –nolockcheck  –nofirststartwizard –convert-to pdf:writer_pdf_Export –outdir /home/workstation/Documents/documentConvert /home/workstation/Documents/documentOriginal/（approved）20200506电子发票.xlsx
         *
         * docker exec -it LibreOfficeOnline --convert-to pdf:writer_pdf_Export –outdir /home/workstation/Documents/documentConvert /home/workstation/Documents/documentOriginal/（approved）20200506电子发票.xlsx
         */
        String cmd = "libreoffice --convert-to pdf:writer_pdf_Export " + documentPath + documentName + " --outdir " + documentTargetPath;
        System.out.println(cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            try {
                // 获取返回状态
                int status = process.waitFor();
                // 销毁process
                process.destroy();
                process = null;
                System.out.println("status -> " + status);
                } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

//        //修改转换后的pdf文件名，前端使用pdfjs展示pdf 文件名不能有中文 处理麻烦 改名即可
//        String pdfFile=outFileString+"/"+fileName.substring(0,fileName.lastIndexOf("."))+".pdf";//转换后的文件地址
//        String finallUrl = outFileString+"/"+pdfName;//用当前毫秒值定义的文件名字
//        if(new File(pdfFile).isFile()){
//            File oldName = new File(pdfFile);
//            File newName = new File(finallUrl);
//            if(oldName.renameTo(newName)) {
//                System.out.println("已重命名");
//            }else {
//                System.out.println("重命名Error");
//            }
//        }
//
//        jsonMap.put("finallUrl",finallUrl);
//        jsonMap.put("filname", fileName);
//        jsonMap.put("pdfname", pdfName);
//
//        System.out.println(jsonMap);
    }
}
