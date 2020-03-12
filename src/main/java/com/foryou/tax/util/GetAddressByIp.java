package com.foryou.tax.util;

import com.foryou.tax.api.bean.ExecuteResult;
import com.foryou.tax.api.constant.StatusCode;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description: IP属地获取
 */
public class GetAddressByIp {

    /**
     *
     * 描述：TODO 对此方法进行描述<br>
     * @param IP
     * @return String
     * 作者:范吉锋<br>
     * 创建时间:2015年9月30日 上午11:58:12
     */
    public static String GetAddressByIp(String IP) {
        String resout = "";
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip="
                    + IP);
            JSONObject obj = JSONObject.fromObject(str);
            JSONObject obj2 = (JSONObject) obj.get("data");
            String code = obj.get("code")+"";
            if (code.equals("0")) {
                resout = obj2.get("country") + "--" + obj2.get("area") + "--"
                        + obj2.get("city") + "--" + obj2.get("isp");
            } else {
                resout = "IP地址有误";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.error(GetAddressByIp.class, e.getMessage(),e);
            resout = "获取IP地址异常：" + e.getMessage();
        }
        return resout;

    }

    /**
     * 通过ip获取地址信息
     * @param IP
     * @return
     */
    public static ExecuteResult<Map<String,String>> GetAddress(String IP) {
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip="
                    + IP);
            JSONObject obj = JSONObject.fromObject(str);
            JSONObject obj2 = (JSONObject) obj.get("data");
            String code = obj.get("code")+"";
            if (code.equals("0")) {
                Map<String,String> map = new HashMap<String, String>();
                map.put("country", obj2.getString("country"));
                map.put("area", obj2.getString("area"));
                map.put("city", obj2.getString("city"));
                map.put("isp", obj2.getString("isp"));
                return new ExecuteResult<Map<String,String>>(map);
            } else {
                return new ExecuteResult<Map<String,String>>(StatusCode.IPError);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.error(GetAddressByIp.class,e.getMessage(),e);
            return new ExecuteResult<Map<String,String>>(StatusCode.GetIPError);
        }

    }

    private static String getJsonContent(String urlStr) {
        try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LoggerUtils.error(GetAddressByIp.class,e.getMessage(),e);
        } catch (IOException e) {
            e.printStackTrace();
            LoggerUtils.error(GetAddressByIp.class,e.getMessage(),e);
        }
        return "";
    }

    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LoggerUtils.error(GetAddressByIp.class,e.getMessage(),e);
        }
        return jsonStr;
    }
}
