package com.foryou.tax.pojo.sms;

import java.io.Serializable;

/**
 * @author ：Raymon
 * @date ：Created in 2020/7/15
 * @description : sms bean
 */
public class SmsPojo implements Serializable {

    /**
     * 用户名
     */
    private String account;
    /**
     * 密码（必填）
     */
    private String password;
    /**
     * 手机号码（必填,多条以英文逗号隔开）
     */
    private String phone;
    /**
     * 短信签名（必填）
     */
    private String sign;
    /**
     * 子号码（必填）
     */
    private String subcode;
    /**
     * 唯一识别
     */
    private String msgid;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 提交网址
     */
    private String submitUrl;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

}
