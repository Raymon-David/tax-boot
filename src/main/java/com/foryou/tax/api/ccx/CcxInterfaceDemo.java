package com.foryou.tax.api.ccx;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ：Raymon
 * @date ：Created in 2020/10/21
 * @description: 中诚信接口
 */
public class CcxInterfaceDemo {

    /**
     *
     */
    private static String url = "https://tapi.ccxcredit.com:443/data-service/identity/auth/md5";
    // 账户
    private static String account = "zcx_ctest";
    // 账户密钥
    private static String key = "test123";

    public static void main(String[] args) throws IOException {

        System.out.println("================注意==================");
        System.out.println("========具体加密字段和签名字段========");
        System.out.println("==============请求地址================");
        System.out.println("==========请查看接口文档==============");

        String name = "张三";
        String cid = "123456199001011233";
        String card = "5555666677778888";
        String mobile = "13800138000";
        String reqTid = "2020091512345678";

        Map<String, String> map = new TreeMap<String, String>();
        map.put("account", account);
        map.put("name", MD5(name).toUpperCase());
        //map.put("mobile", MD5Util.MD5(mobile).toUpperCase());
        map.put("cid", MD5(cid).toUpperCase());
        //map.put("reqTid", reqTid);

        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();
            params.append(key).append(value);
        }
        params.append(key);
        System.out.println(params);

        String sign = MD5(params.toString()).toUpperCase();
        System.out.println(sign);
        map.put("sign", sign);

        HttpPost httpPost = new HttpPost(url);

        System.out.println(JSON.toJSONString(map));
        //httpPost.setEntity(new StringEntity(new Gson().toJson(map), "application/json", "utf-8"));
        httpPost.setEntity(new StringEntity(JSON.toJSONString(map), "application/json", "utf-8"));
        //httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.setHeader("content-type", "application/json");

        //CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpPost);
        if (response != null) {
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                String s = EntityUtils.toString(entity);

                System.out.println("https传输成功之后返回信息: " + s);

                EntityUtils.consume(entity);
            /* HttpEntity entity = response.getEntity();
                String s = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
             JSONObject jsonObject = JSONObject.fromObject(s);
             String r = jsonObject.getString("resCode");
                System.out.println(r);*/
            } else {
                System.out.println("返回code:" + response.getStatusLine().getStatusCode());
            }
        }
    }

    public final static String MD5(String s) {
        //char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        // update MD5 string to lower-case to adapt to UMP test
        // TODO: check lower-case or upperp-case are the MD5 standard
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes("UTF-8");

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
