package com.foryou.tax.util.eleinvoice;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceDetail;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import com.foryou.tax.service.eleinvoice.EleInvoiceDetailService;
import com.foryou.tax.service.eleinvoice.EleInvoiceInfoService;
import com.foryou.tax.util.LoggerUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/17
 * @description : 电子发哦调用金税接口开具发票，封装报文工具类
 */
public class EleInvoiceSubmitXmlUtil {

    private static String returnNode = "return";   //return节点
    private static String msgCodeNode = "msgCode"; //msgCode节点
    private static String msgNode = "msg";          //msg节点
    private static String code = "0000";            //返回结果 0000 成功

    private static  String nodeName = "";
    private static  String returnStr = "";
    private static  String msgCode = "";
    private static  String msg = "";

    //封装报文
    public static String eleInvoiceSubmitXml(ALLInvoiceInfo allInvoiceInfo, EleInvoiceInfoService eleInvoiceInfoService, EleInvoiceDetailService eleInvoiceDetailService){
        StringBuffer stringBuffer = new StringBuffer();
        /**
         * 报文 header 命名空间
         */
        stringBuffer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ejinshui.com/\">\n");
        stringBuffer.append("<soapenv:Header/>\n");
        stringBuffer.append("<soapenv:Body>\n");
        stringBuffer.append("<ser:submitEInvoiceInfo>");
        /**
         * 报文格式
         */
        stringBuffer.append("<arg0><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        stringBuffer.append("<InvoInfo>");

        /**
         * 全部发票表信息(通过全部发票表 ALLInvoiceInfo ID 找电子发票表 EleInvoice 只有一条)
         */
        EleInvoiceInfo eleInvoiceInfo = new EleInvoiceInfo();
        eleInvoiceInfo.setInvoiceId(allInvoiceInfo.getInvoiceId());
        List<EleInvoice> EleInvoices = eleInvoiceInfoService.getEleInvoiceInfo(eleInvoiceInfo);
        EleInvoiceInfo eleInvoiceData = EleInvoices.get(0);
        //报文详细内容
        //流水号
        stringBuffer.append("<swno>");
        stringBuffer.append(eleInvoiceData.getSerialNum());
        stringBuffer.append("</swno>");
        //销方税号
        stringBuffer.append("<saleTax>");
        stringBuffer.append(eleInvoiceData.getSaleTax());
        stringBuffer.append("</saleTax>");
        //购方名称
        stringBuffer.append("<custName>");
        stringBuffer.append(eleInvoiceData.getCustName());
        stringBuffer.append("</custName>");
        //购方税号
        stringBuffer.append("<custTaxNo>");
        if("".equals(eleInvoiceData.getCustTaxNo()) || eleInvoiceData.getCustTaxNo() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getCustTaxNo());
        }
        stringBuffer.append("</custTaxNo>");
        //购方地址
        stringBuffer.append("<custAddr>");
        if("".equals(eleInvoiceData.getCustAddr()) || eleInvoiceData.getCustAddr() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getCustAddr());
        }
        stringBuffer.append("</custAddr>");
        //购货方手机号
        stringBuffer.append("<custTelephone>");
        if("".equals(eleInvoiceData.getCustTelephone()) || eleInvoiceData.getCustTelephone() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getCustTelephone());
        }
        stringBuffer.append("</custTelephone>");
        //购方固定电话
        stringBuffer.append("<custPhone>");
        if("".equals(eleInvoiceData.getCustPhone()) || eleInvoiceData.getCustPhone() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getCustPhone());
        }
        stringBuffer.append("</custPhone>");
        //购方邮箱
        stringBuffer.append("<custEmail>");
        if("".equals(eleInvoiceData.getCustEmail()) || eleInvoiceData.getCustEmail() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getCustEmail());
        }
        stringBuffer.append("</custEmail>");
        //开户行+账号
        stringBuffer.append("<custBankAccount>");
        if("".equals(eleInvoiceData.getCustBankAccount()) || eleInvoiceData.getCustBankAccount() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getCustBankAccount());
        }
        stringBuffer.append("</custBankAccount>");
        //购货方企业类型
        stringBuffer.append("<custType>");
        stringBuffer.append(eleInvoiceData.getCustType());
        stringBuffer.append("</custType>");
        //备注
        stringBuffer.append("<invoMemo>");
        if("".equals(eleInvoiceData.getEleInvoiceMemo()) || eleInvoiceData.getEleInvoiceMemo() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getEleInvoiceMemo());
        }
        stringBuffer.append("</invoMemo>");
        //发票类型
        stringBuffer.append("<invType>");
        stringBuffer.append(eleInvoiceData.getEleInvoiceType());
        stringBuffer.append("</invType>");
        //单据日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date;
        try {
            date = sdf.format(eleInvoiceData.getBillDate());
            stringBuffer.append("<billDate>");
            stringBuffer.append(date);
            stringBuffer.append("</billDate>");
        }catch (Exception e){
            e.printStackTrace();
        }
        //退货单号
        stringBuffer.append("<thdh>");
        if("".equals(eleInvoiceData.getCancelNum()) || eleInvoiceData.getCancelNum() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getCancelNum());
        }
        stringBuffer.append("</thdh>");
        //开票类型 1代表正票
        stringBuffer.append("<billType>");
        stringBuffer.append(eleInvoiceData.getBillType());
        stringBuffer.append("</billType>");
        //特殊冲红标志
        stringBuffer.append("<specialRedFlag>");
        stringBuffer.append(eleInvoiceData.getSpecialRedFlag());
        stringBuffer.append("</specialRedFlag>");
        //操作代码
        stringBuffer.append("<operationCode>");
        stringBuffer.append(eleInvoiceData.getOperationCode());
        stringBuffer.append("</operationCode>");
        //开票员
        stringBuffer.append("<kpy>");
        stringBuffer.append(eleInvoiceData.getIssuer());
        stringBuffer.append("</kpy>");
        //收款员
        stringBuffer.append("<sky>");
        if("".equals(eleInvoiceData.getPayee()) || eleInvoiceData.getPayee() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getPayee());
        }
        stringBuffer.append("</sky>");
        //复核人
        stringBuffer.append("<fhr>");
        if("".equals(eleInvoiceData.getReviewer()) || eleInvoiceData.getReviewer() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getReviewer());
        }
        stringBuffer.append("</fhr>");
        //原发票代码
        stringBuffer.append("<yfpdm>");
        if("".equals(eleInvoiceData.getFormerInvoiceCode()) || eleInvoiceData.getFormerInvoiceCode() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getFormerInvoiceCode());
        }
        stringBuffer.append("</yfpdm>");
        //原发票号码
        stringBuffer.append("<yfphm>");
        if("".equals(eleInvoiceData.getFormerInvoiceNum()) || eleInvoiceData.getFormerInvoiceNum() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getFormerInvoiceNum());
        }
        stringBuffer.append("</yfphm>");
        //冲红原因
        stringBuffer.append("<chyy>");
        if("".equals(eleInvoiceData.getRedReason()) || eleInvoiceData.getRedReason() == null){
            stringBuffer.append("");
        }else{
            stringBuffer.append(eleInvoiceData.getRedReason());
        }
        stringBuffer.append("</chyy>");

        //行表信息
        stringBuffer.append("<Orders>");
        stringBuffer.append("<Order>");

        List<EleInvoiceDetail> eleInvoiceDetails = eleInvoiceDetailService.getEleInvoiceDetailInfo(eleInvoiceData.getEleInvoiceId());
        /**
         * 订单号
         * (因为之前存行表的时候，取的是合同编号，所以虽然发票行信息不一致，
         * 但是它们都是同一个合同，所以合同编号随便取一条数据的都OK)
         *
         */
        stringBuffer.append("<billNo>");
        stringBuffer.append(eleInvoiceDetails.get(0).getBillNo());
        stringBuffer.append("</billNo>");

        stringBuffer.append("<Items>");

        /**
         * 根据行信息来判断是否多条
         */
        for(int i = 0; i < eleInvoiceDetails.size(); i++){
            EleInvoiceDetail eleInvoiceDetail = eleInvoiceDetails.get(i);
            stringBuffer.append("<item>");
            //商品名称
            stringBuffer.append("<name>");
            stringBuffer.append(eleInvoiceDetail.getBillName());
            stringBuffer.append("</name>");
            //商品编号（税收 分类编码）
            stringBuffer.append("<code>");
            stringBuffer.append(eleInvoiceDetail.getBillCode());
            stringBuffer.append("</code>");
            //发票行性质  0代表正常行
            stringBuffer.append("<lineType>");
            stringBuffer.append(eleInvoiceDetail.getLineType());
            stringBuffer.append("</lineType>");
            //规格型号
            stringBuffer.append("<spec>");
            if("".equals(eleInvoiceDetail.getSpec()) || eleInvoiceDetail.getSpec() == null){
                stringBuffer.append("");
            }else{
                stringBuffer.append(eleInvoiceDetail.getSpec());
            }
            stringBuffer.append("</spec>");
            //计量单位
            stringBuffer.append("<unit>");
            stringBuffer.append(eleInvoiceDetail.getUnit());
            stringBuffer.append("</unit>");
            //税率
            stringBuffer.append("<taxRate>");
            stringBuffer.append(eleInvoiceDetail.getTaxRate());
            stringBuffer.append("</taxRate>");
            //数量
            stringBuffer.append("<quantity>");
            stringBuffer.append(eleInvoiceDetail.getTaxRquantityate());
            stringBuffer.append("</quantity>");
            //单价
            stringBuffer.append("<taxPrice>");
            stringBuffer.append(eleInvoiceDetail.getTaxPrice());
            stringBuffer.append("</taxPrice>");
            //含税金额
            stringBuffer.append("<totalAmount>");
            stringBuffer.append(eleInvoiceDetail.getTotalAmount());
            stringBuffer.append("</totalAmount>");
            //税收优惠政策 标志
            stringBuffer.append("<yhzcbs>");
            stringBuffer.append(eleInvoiceDetail.getYhzcbs());
            stringBuffer.append("</yhzcbs>");
            //享受税收优惠 政策内容
            stringBuffer.append("<yhzcnr>");
            if("".equals(eleInvoiceDetail.getYhzcnr()) || eleInvoiceDetail.getYhzcnr() == null){
                stringBuffer.append("");
            }else{
                stringBuffer.append(eleInvoiceDetail.getYhzcnr());
            }
            stringBuffer.append("</yhzcnr>");
            //零税率标识  空表示非0税率
            stringBuffer.append("<lslbs>");
            if("".equals(eleInvoiceDetail.getLslbs()) || eleInvoiceDetail.getLslbs() == null){
                stringBuffer.append("");
            }else{
                stringBuffer.append(eleInvoiceDetail.getLslbs());
            }
            stringBuffer.append("</lslbs>");
            //自行编码
            stringBuffer.append("<zxbm>");
            if("".equals(eleInvoiceDetail.getZxbm()) || eleInvoiceDetail.getZxbm() == null){
                stringBuffer.append("");
            }else{
                stringBuffer.append(eleInvoiceDetail.getZxbm());
            }
            stringBuffer.append("</zxbm>");
            //扣除额
            stringBuffer.append("<kce>");
            if("".equals(eleInvoiceDetail.getKce()) || eleInvoiceDetail.getKce() == null){
                stringBuffer.append("");
            }else{
                stringBuffer.append(eleInvoiceDetail.getKce());
            }
            stringBuffer.append("</kce>");
            stringBuffer.append("</item>");
        }
        stringBuffer.append("</Items>");
        stringBuffer.append("</Order>");
        stringBuffer.append("</Orders>");

        //格式结束
        stringBuffer.append("</InvoInfo>]]></arg0>");
        //报文 footer 命名空间结束
        stringBuffer.append("</ser:submitEInvoiceInfo>\n");
        stringBuffer.append("</soapenv:Body>\n");
        stringBuffer.append("</soapenv:Envelope>");

        LoggerUtils.debug(EleInvoiceSubmitXmlUtil.class, "电子发票发送金税接口报文" + stringBuffer.toString());
        return stringBuffer.toString();
    }


    /**
     * 调用发送接口报文封装
     */
    public static JSONObject postData(String xml)  {
        String  acrWsdlUrl = "";
        try {
            Properties properties = new Properties();
            InputStream is = PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(is);
            acrWsdlUrl = properties.getProperty("eleinvoice.webservice.url");
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
                GetMarginXmlUtil.readInputStream(responseString);
                if(code.equals(msgCode)){
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
            jsonObject.put("flag", "false");
            jsonObject.put("errorMessage", e.getMessage());
        }
        return jsonObject;
    }
}