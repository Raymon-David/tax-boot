package com.foryou.tax.util.eleinvoice;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import com.foryou.tax.service.eleinvoice.EleInvoiceInfoService;
import com.foryou.tax.util.LoggerUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import static com.foryou.tax.util.eleinvoice.GetMarginXmlUtil.readInputStream;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/26
 * @description: 电子发票下载 util
 */
public class EleInvoiceDownloadXmlUtil {

    private static String returnNode = "return";   //return节点
    private static String msgCodeNode = "msgCode"; //msgCode节点
    private static String msgNode = "msg";          //msg节点
    private static String swnoNode = "swno";        //swno节点
    private static String fpdmNode = "fpdm";        //fpdm节点
    private static String fphmNode = "fphm";        //fphm节点
    private static String pdfUrlNode = "pdf_url";   //pdf_url节点
    private static String kprqNode = "kprq";        //kprq节点
    private static String code = "0000";            //返回结果 0000 成功

    private static  String nodeName = "";
    private static  String returnStr = "";
    private static  String msgCode = "";    //返回code
    private static  String msg = "";        //异常信息(如果msgCode = '0000'表示无异常，则为空)
    private static  String swno = "";      //流水号
    private static  String fpdm = "";     //发票代码
    private static  String fphm = "";     //发票号码
    private static  String pdfUrl = "";   //pdf URL
    private static  String kprq = "";    //开票时间

    //封装报文
    public static String getXml(AllInvoiceInfo allInvoiceInfo, EleInvoiceInfoService eleInvoiceInfoService){
        StringBuffer stringBuffer = new StringBuffer();
        //报文 header 命名空间
        stringBuffer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ejinshui.com/\">\n");
        stringBuffer.append("<soapenv:Header/>\n");
        stringBuffer.append("<soapenv:Body>\n");
        stringBuffer.append("<ser:downloadEInvoiceInfo>");

        //报文格式
        stringBuffer.append("<arg0><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        stringBuffer.append("<InvoInfo>");

        //头表信息(通过发票头表ID 找电子发票头表 只有一条)
        EleInvoiceInfo eleInvoiceInfo = new EleInvoiceInfo();
        eleInvoiceInfo.setInvoiceId(allInvoiceInfo.getInvoiceId());
        EleInvoiceInfo eleInvoiceInfo1 = eleInvoiceInfoService.getEleInvoiceInfo(eleInvoiceInfo);
        //报文详细内容
        //流水号
        stringBuffer.append("<swno>");
        stringBuffer.append(eleInvoiceInfo1.getSerialNum());
        stringBuffer.append("</swno>");
        //销方税号
        stringBuffer.append("<saleTax>");
        stringBuffer.append(eleInvoiceInfo1.getSaleTax());
        stringBuffer.append("</saleTax>");

        //格式结束
        stringBuffer.append("</InvoInfo>]]></arg0>");

        //报文 footer 命名空间结束
        stringBuffer.append("</ser:downloadEInvoiceInfo>\n");
        stringBuffer.append("</soapenv:Body>\n");
        stringBuffer.append("</soapenv:Envelope>");

        LoggerUtils.debug(EleInvoiceDownloadXmlUtil.class, stringBuffer.toString());

        return stringBuffer.toString();
    }


    //调用接口
    public static JSONObject post(String xml, IRequest iRequest, AcrEleInvoiceHdMapper acrEleInvoiceHdMapper, IHlsFndAtmAttachmentMultiService iHlsFndAtmAttachmentMultiService, IHlsFndAtmAttachmentService iHlsFndAtmAttachmentService, HlsFndAtmAttachmentMultiMapper atmAttachmentMultiMapper)  {
        String  acrWsdlUrl = "";
        String  pdfAddress = "";
        try {
            Properties properties = new Properties();
            InputStream is = PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(is);
            acrWsdlUrl = properties.getProperty("eleinvoice.webservice.url");
            pdfAddress = properties.getProperty("eleinvoice.pdfAddress");
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        HttpClient client = new HttpClient();
        PostMethod myPost = new PostMethod(acrWsdlUrl);
        client.getParams().setSoTimeout(60*1000);
        try{
            myPost.setRequestEntity(new StringRequestEntity(xml,"text/xml","utf-8"));
            int statusCode = client.executeMethod(myPost);
            if(statusCode == HttpStatus.SC_OK){
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while((count = bis.read(bytes))!= -1){
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                //解析返回的数据
                String responseString = new String(strByte,0,strByte.length,"utf-8");
                jsonObject.put("XmlReturn",  responseString);
                readInputStream(responseString);
                if(code.equals(msgCode)){
                    //解析url，并且下载pdf
                    downloadFile(pdfUrl,pdfAddress,swno + ".pdf");
                    //下载到服务器磁盘之后，更新发票信息到主表，然后把服务器上面的附件关联上主表
                    //第一步，更新信息回主表
                    LoggerUtils.debug(EleInvoiceDownloadXmlUtil.class, pdfUrl);
                    acrEleInvoiceHdMapper.updateAceEleHdInfo(swno,fpdm,fphm,pdfUrl,kprq);
                    //第二步，把附件关联上电子发票主表
                    HlsFndAtmAttachmentMulti hlsFndAtmAttachmentMulti = new HlsFndAtmAttachmentMulti();
                    //先找到swno对应的 电子发票头表ID
                    Long eleInvoiceHdId = acrEleInvoiceHdMapper.getEleInvoiceHdId(swno);
                    hlsFndAtmAttachmentMulti.setTableName("ACR_ELE_INVOICE_HD");
                    hlsFndAtmAttachmentMulti.setTablePkValue(eleInvoiceHdId);
                    hlsFndAtmAttachmentMulti = iHlsFndAtmAttachmentMultiService.insertSelective(iRequest,hlsFndAtmAttachmentMulti);

                    HlsFndAtmAttachment hlsFndAtmAttachment = new HlsFndAtmAttachment();
                    hlsFndAtmAttachment.setSourceTypeCode("fnd_atm_attachment_multi");
                    hlsFndAtmAttachment.setSourcePkValue(String.valueOf(hlsFndAtmAttachmentMulti.getRecordId()));
                    hlsFndAtmAttachment.setFileTypeCode("pdf");
                    hlsFndAtmAttachment.setMimeType("application/pdf");
                    hlsFndAtmAttachment.setFileName(swno + ".pdf");
                    Long fileSize = 0L;
                    File file = new File(pdfAddress + swno + ".pdf");
                    if (file.exists() && file.isFile()) {
                        fileSize = file.length();
                    }
                    hlsFndAtmAttachment.setFileSize(fileSize);
                    hlsFndAtmAttachment.setFilePath(pdfAddress + swno + ".pdf");
                    hlsFndAtmAttachment.setInterfaceFlag("Y");
                    hlsFndAtmAttachment = iHlsFndAtmAttachmentService.insertSelective(iRequest,hlsFndAtmAttachment);

                    hlsFndAtmAttachmentMulti.setAttachmentId(hlsFndAtmAttachment.getAttachmentId());
                    atmAttachmentMultiMapper.updateAttachmentMulti(hlsFndAtmAttachmentMulti);

                    jsonObject.put("flag", "true");
                }else{
                    jsonObject.put("flag", "false");
                    jsonObject.put("errorMessage", msg);
                }
                bos.close();
                bis.close();
            }else{
                jsonObject.put("flag", "false");
                jsonObject.put("errorMessage", "调用接口异常" + statusCode);
            }
            myPost.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("flag", "false");
            jsonObject.put("errorMessage", e.getMessage());
        }
        return jsonObject;
    }

    public static void downloadFile(String urlPath, String downloadDir, String fileName) {
        File file = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            // 文件大小
            int fileLength = httpURLConnection.getContentLength();
            // 文件
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

            String path = downloadDir + File.separatorChar + fileName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);

            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
            bin.close();
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
