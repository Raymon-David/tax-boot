package com.foryou.tax.util;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/12
 * @description:
 */
public class MemberTools {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }



    /**得到cookie值*/
    public static String getCookieValue(HttpServletRequest request,String name){
        Cookie[] cookie = request.getCookies();
        if(cookie!=null){
            for(Cookie c:cookie){
                if(c.getName().equals(name)){
                    return c.getValue();
                }
            }
        }
        return null;
    }

    /**得到cookie值*/
    public static String getCookieContiansValue(HttpServletRequest request,String name){
        Cookie[] cookie = request.getCookies();
        if(cookie!=null){
            for(Cookie c:cookie){
                if(c.getName().indexOf(name) > 0){
                    return c.getValue();
                }
            }
        }
        return null;
    }

    /**删除cookie*/
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name){
        Cookie[] cookie = request.getCookies();
        if(cookie!=null){
            for(Cookie c:cookie){
                if(c.getName().equals(name)){
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
        }
    }

    /**得到加密的串
     * @throws NoSuchAlgorithmException */
    public static String encryption(String data) throws NoSuchAlgorithmException {
        return encryptBASE64(encryptSHA(data));
    }

    public static byte[] encryptSHA(String data) throws NoSuchAlgorithmException  {
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        sha.update(data.getBytes());
        return sha.digest();

    }
    public static byte[] decryptBASE64(String key){
        return new Base64().decode(key);
    }
    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key){
        String s=new Base64().encodeToString(key);
        return s.substring(0,s.length()-1);
    }
}
