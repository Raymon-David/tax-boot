package com.foryou.tax.api.jacob.documentconvertbylibreoffice;

import com.foryou.tax.util.LoggerUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
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
public class ConvertPdfByLibreOffice {
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
    public Map<String, Object> pdfConvert(String documentName) {
        String documentPath = null;
        String documentTargetPath = null;

        //定义map 绑定传给前台参数
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        // 开始时间
        long start = System.currentTimeMillis();
        try {
            Properties properties = new Properties();
            InputStream is = PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            properties.load(is);
            documentPath = properties.getProperty("document_path");//文件路径
            documentTargetPath = properties.getProperty("document_target_path"); //输出pdf文件路径
        }catch (Exception e){
            e.printStackTrace();
        }

        /**
         * 利用cmd命令 使用libreoffice转换pdf文件
         *
         * docker exec -it LibreOfficeOnline /bin/soffice –headless  –invisible –norestore –nodefault –nolockcheck  –nofirststartwizard –convert-to pdf:writer_pdf_Export –outdir /home/workstation/Documents/documentConvert /home/workstation/Documents/documentOriginal/（approved）20200506电子发票.xlsx
         *
         * docker exec -it LibreOfficeOnline --convert-to pdf:writer_pdf_Export –outdir /home/workstation/Documents/documentConvert /home/workstation/Documents/documentOriginal/（approved）20200506电子发票.xlsx
         *
         * https://blog.csdn.net/u013199013/article/details/83142817
         *
         * https://www.csdn.net/gather_25/MtTacgzsNTA3MC1ibG9n.html
         */
        String cmd = "libreoffice --convert-to pdf:writer_pdf_Export " + documentPath + documentName + " --outdir " + documentTargetPath;
        LoggerUtils.debug(getClass(), "command is : " + cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            try {
                // 获取返回状态
                int status = process.waitFor();
                // 销毁process
                process.destroy();
                System.out.println("status -> " + status);
                LoggerUtils.debug(getClass(), "process status -> " + status);
                } catch (InterruptedException e) {
                LoggerUtils.error(getClass(), e.getMessage());
            }
        } catch (IOException e) {
            LoggerUtils.error(getClass(), e.getMessage());
        }

        // 获取文件名称
        char[] input = documentName.trim().toCharArray();
        // 获取文件所在的路径名称
        String outputFileName = "";

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    outputFileName += temp[0];
                } else {
                    outputFileName += Character.toString(input[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        //修改转换后的pdf文件名，前端使用pdfjs展示pdf 文件名不能有中文 处理麻烦 改名即可
        String pdfFile = outputFileName.substring(0, outputFileName.lastIndexOf(".")) + ".pdf";//转换后的文件地址
        String finallUrl = documentTargetPath + pdfFile;//转换后的文件地址，可供前台预览

        String newDocumentName = documentName.substring(0, documentName.lastIndexOf(".")) + ".pdf";
        String pdfPath = documentTargetPath + newDocumentName;//转换后的文件地址，可供前台下载
        LoggerUtils.debug(getClass(), "newDocumentName is: " + newDocumentName);
        // 结束时间
        long end = System.currentTimeMillis();
        LoggerUtils.debug(getClass(), "转换成功，用时：" + (end - start) + "ms");

        jsonMap.put("finallUrl",finallUrl);
        jsonMap.put("pdffile", pdfFile);
        jsonMap.put("pdfname", newDocumentName);
        jsonMap.put("pdfpath", pdfPath);
        System.out.println(jsonMap);

        return jsonMap;
    }
}
