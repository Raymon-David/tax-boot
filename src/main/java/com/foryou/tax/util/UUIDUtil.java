package com.foryou.tax.util;

import java.util.UUID;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description:
 */
public class UUIDUtil {

    /**
     * 生成一个唯一数
     * @param str 固定值
     * @param ip
     * @param dateFormat 日期格式 如 yyyyMMddHHmmssSSS
     * @return
     */
    public static String generateId(String str, String ip,String dateFormat){

        return ip+DateUtil.getNow("yyyyMMddHHmmssSSS");

    }

    /**
     *JDK自带的UUID,
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
