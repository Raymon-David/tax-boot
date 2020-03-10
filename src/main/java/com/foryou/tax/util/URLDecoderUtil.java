package com.foryou.tax.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/10
 * @description :
 */
public class URLDecoderUtil {
    public static String decode(String str,String encode){
        try {
            return URLDecoder.decode(str, encode);
        } catch (UnsupportedEncodingException e) {
        }
        return str;
    }
}
