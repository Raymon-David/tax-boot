package com.foryou.tax.util.eleinvoice;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.util.LoggerUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/17
 * @description : 查询电子发票开票余量 util
 */
public class GetMarginXmlUtil {

    private static String returnNode = "return";                     //return节点
    private static String syfpfsCodeNode = "SYFPFS";                 //SYFPFS节点
    private static String returnCodeNode = "RETURNCODE";             //RETURNCODE节点
    private static String returnMessageNode = "RETURNMESSAGE";       //RETURNMESSAGE节点
    private static String code = "0000";                              //返回结果 0000 成功

    private static  String nodeName = "";
    private static  String returnStr = "";
    private static  String syfpfs = "";             //解析后得到的数量
    private static  String returnCode = "";         //节点code
    private static  String returnMessage = "";      //异常信息

    /**
     * 查询电子发票开票余量xml
     * @return
     */
    public static String getMarginXml(){
        String saleTax = "";
        try {
            Properties properties = new Properties();
            /**
             * 税号暂时存放在config，日后可以存表
             */
            InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(is);
            saleTax = properties.getProperty("eleinvoice.saleTax");
        }catch (Exception e){
            e.printStackTrace();
        }

        StringBuffer stringBuffer = new StringBuffer();
        //报文 header 命名空间
        stringBuffer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ejinshui.com/\">\n");
        stringBuffer.append("<soapenv:Header/>\n");
        stringBuffer.append("<soapenv:Body>\n");
        stringBuffer.append("<ser:getKPYL>");
        //报文格式
        stringBuffer.append("<arg0><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        stringBuffer.append("<InvoInfo>");
        //报文详细内容
        //销方税号
        stringBuffer.append("<saleTax>");
        stringBuffer.append(saleTax);
        stringBuffer.append("</saleTax>");
        //格式结束
        stringBuffer.append("</InvoInfo>]]></arg0>");
        //报文 footer 命名空间结束
        stringBuffer.append("</ser:getKPYL>\n");
        stringBuffer.append("</soapenv:Body>\n");
        stringBuffer.append("</soapenv:Envelope>");

        LoggerUtils.debug(GetMarginXmlUtil.class, "电子发票查询余量报文" + stringBuffer.toString());

        return stringBuffer.toString();
    }


    /**
     * 调用请求接口
     * @param xml
     * @return
     */
    public static JSONObject postData(String xml)  {
        String eleWsdlUrl = null;
        try {
            Properties properties = new Properties();
            InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(is);
            eleWsdlUrl = properties.getProperty("eleinvoice.webservice.url");
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        HttpClient client = new HttpClient();
        PostMethod myPost = new PostMethod(eleWsdlUrl);
        client.getParams().setSoTimeout(60*1000);
        try{
            myPost.setRequestEntity(new StringRequestEntity(xml,"text/xml","utf-8"));
            LoggerUtils.debug(GetMarginXmlUtil.class, myPost.toString());

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
                String responseString = new String(strByte,0,strByte.length, StandardCharsets.UTF_8);
                readInputStream(responseString);
                if(code.equals(returnCode)){
                    jsonObject.put("flag", "true");
                    jsonObject.put("acrEleNumber",syfpfs);
                }else{
                    jsonObject.put("flag", "false");
                    jsonObject.put("errorMessage", returnMessage);
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
            jsonObject.put("flag", "false");
            jsonObject.put("errorMessage", e.getMessage());
        }
        return jsonObject;
    }

    public static void readInputStream(String responseString){
        try{
            //先截取 return节点后面的内容
            Document document = DocumentHelper.parseText(responseString);
            final Element root = document.getRootElement();
            getNodes(root);
            //然后把return后面的内容继续分析
            if(returnNode.equals(nodeName)){
                Document doc = DocumentHelper.parseText(returnStr);
                final Element element = doc.getRootElement();
                analysisXml(element);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void getNodes(final Element node) {
        if(returnNode.equals(node.getName())){
            nodeName = node.getName();
            returnStr = node.getTextTrim();
        }
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (final Element e : listElement) {// 遍历所有一级子节点
            getNodes(e);// 递归
        }
    }

    public static void analysisXml(final Element node) {
        if(syfpfsCodeNode.equals(node.getName())){
            syfpfs = node.getTextTrim();
        }
        if(returnCodeNode.equals(node.getName())){
            returnCode = node.getTextTrim();
        }
        if(returnMessageNode.equals(node.getName())){
            returnMessage = node.getTextTrim();
        }
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (final Element e : listElement) {// 遍历所有一级子节点
            analysisXml(e);// 递归
        }
    }
}
