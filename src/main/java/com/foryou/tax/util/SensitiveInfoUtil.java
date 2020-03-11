package com.foryou.tax.util;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description: 敏感信息屏蔽工具
 */
public class SensitiveInfoUtil {

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param fullName
     * @return
     */
    public static String chineseName(String fullName) {
        String masking = "";
        if(org.apache.commons.lang.StringUtils.isBlank(masking)){
            masking = "N";
        }
        if("N".equals(masking)){
            return fullName;
        }
        if (org.apache.commons.lang.StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = org.apache.commons.lang.StringUtils.left(fullName, 1);
        return org.apache.commons.lang.StringUtils.rightPad(name, org.apache.commons.lang.StringUtils.length(fullName), "*");
    }

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param familyName
     * @param givenName
     * @return
     */
    public static String chineseName(String familyName, String givenName) {
        String masking = "";
        if(org.apache.commons.lang.StringUtils.isBlank(masking)){
            masking = "N";
        }
        if("N".equals(masking)){
            return familyName + givenName;
        }

        if (org.apache.commons.lang.StringUtils.isBlank(familyName) || org.apache.commons.lang.StringUtils.isBlank(givenName)) {
            return "";
        }
        return chineseName(familyName + givenName);
    }

    /**
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     *
     * @param id
     * @return
     */
    public static String idCardNum(String id) {
        String masking = "";
        if(org.apache.commons.lang.StringUtils.isBlank(masking)){
            masking = "N";
        }
        if("N".equals(masking)){
            return id;
        }

        if (org.apache.commons.lang.StringUtils.isBlank(id)) {
            return "";
        }
        return org.apache.commons.lang.StringUtils.left(id, 6).concat(org.apache.commons.lang.StringUtils.removeStart(org.apache.commons.lang.StringUtils.leftPad(org.apache.commons.lang.StringUtils.right(id, 4), org.apache.commons.lang.StringUtils.length(id), "*"), "******"));

    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     *
     * @param num
     * @return
     */
    public static String mobilePhone(String num) {
        String masking = "";
        if(org.apache.commons.lang.StringUtils.isBlank(masking)){
            masking = "N";
        }
        if("N".equals(masking)){
            return num;
        }
        if (org.apache.commons.lang.StringUtils.isBlank(num)) {
            return "";
        }
        return org.apache.commons.lang.StringUtils.left(num, 3).concat(org.apache.commons.lang.StringUtils.removeStart(org.apache.commons.lang.StringUtils.leftPad(org.apache.commons.lang.StringUtils.right(num, 4), org.apache.commons.lang.StringUtils.length(num), "*"), "***"));
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param address
     * @param sensitiveSize
     *            敏感信息长度
     * @return
     */
    public static String address(String address, int sensitiveSize) {
        String masking = "";
        if(org.apache.commons.lang.StringUtils.isBlank(masking)){
            masking = "N";
        }
        if("N".equals(masking)){
            return address;
        }
        if (org.apache.commons.lang.StringUtils.isBlank(address)) {
            return "";
        }
        int length = org.apache.commons.lang.StringUtils.length(address);
        return org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     *
     * @param email
     * @return
     */
    public static String email(String email) {
        String masking = "";
        if(org.apache.commons.lang.StringUtils.isBlank(masking)){
            masking = "N";
        }
        if("N".equals(masking)){
            return email;
        }
        if (org.apache.commons.lang.StringUtils.isBlank(email)) {
            return "";
        }
        int index = org.apache.commons.lang.StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.left(email, 1), index, "*").concat(org.apache.commons.lang.StringUtils.mid(email, index, org.apache.commons.lang.StringUtils.length(email)));
        }
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     *
     * @param cardNum
     * @return
     */
    public static String bankCard(String cardNum) {
        String masking = "";
        if(org.apache.commons.lang.StringUtils.isBlank(masking)){
            masking = "N";
        }
        if("N".equals(masking)){
            return cardNum;
        }
        if (org.apache.commons.lang.StringUtils.isBlank(cardNum)) {
            return "";
        }
        return org.apache.commons.lang.StringUtils.left(cardNum, 6).concat(org.apache.commons.lang.StringUtils.removeStart(org.apache.commons.lang.StringUtils.leftPad(org.apache.commons.lang.StringUtils.right(cardNum, 4), org.apache.commons.lang.StringUtils.length(cardNum), "*"), "******"));
    }
}
