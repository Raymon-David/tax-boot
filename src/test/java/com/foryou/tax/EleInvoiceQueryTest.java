package com.foryou.tax;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.util.eleinvoice.EleInvoiceSubmitXmlUtil;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/14
 * @description: 电子发票查询已开发票接口测试类
 */
public class EleInvoiceQueryTest {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:EjinsuiIntf-IEjinsui\">\n");
        stringBuffer.append("<soapenv:Header/>");
        stringBuffer.append("<soapenv:Body>\n");
        stringBuffer.append("<urn:QueryInv soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TypeCode", "011001800311");
        jsonObject.put("InvNo", "80884305");
        jsonObject.put("InvKind", "0");

        // 报文详细内容
        stringBuffer.append("<Vjson xsi:type=\"xsd:string\">");
        stringBuffer.append(jsonObject.toString());
        stringBuffer.append("</Vjson>\n");

        // 报文 footer 命名空间结束
        stringBuffer.append("</urn:QueryInv>\n");
        stringBuffer.append("</soapenv:Body>\n");
        stringBuffer.append("</soapenv:Envelope>");

        System.out.println(stringBuffer.toString());

        JSONObject json = EleInvoiceSubmitXmlUtil.postData(stringBuffer.toString());
        System.out.println(json.toJSONString());
        System.out.println("https://github.com/fuce1314/Springboot_v2/blob/master/src/main/resources/templates/admin/file/list.html");
    }
}
