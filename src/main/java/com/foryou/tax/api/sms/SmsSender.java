package com.foryou.tax.api.sms;

import com.dahantc.api.sms.json.JSONHttpClient;
import com.foryou.tax.pojo.sms.SmsPojo;
import com.foryou.tax.util.LoggerUtils;
import org.apache.commons.httpclient.URIException;

import java.util.UUID;

/**
 * @author ：Raymon
 * @date ：Created in 2020/7/15
 * @description : 短信发送类
 */
public class SmsSender {

    public String send(SmsPojo smsPojo) {

        String sendResponse = null;
        try {
            LoggerUtils.debug(getClass(), "短信发送开始");
            JSONHttpClient jsonHttpClient = new JSONHttpClient(smsPojo.getSubmitUrl());
            jsonHttpClient.setRetryCount(1);
            sendResponse = jsonHttpClient.sendSms(
                    smsPojo.getAccount(),
                    smsPojo.getPassword(),
                    smsPojo.getPhone(),
                    smsPojo.getContent(),
                    smsPojo.getSign(),
                    smsPojo.getSubcode(),
                    smsPojo.getMsgid(),
                    smsPojo.getSendTime());

            LoggerUtils.debug(getClass(), "提交单条普通短信响应：" + sendResponse);
        } catch (URIException e) {
            e.printStackTrace();
            LoggerUtils.error(getClass(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            LoggerUtils.error(getClass(), e.getMessage());
        }

        return sendResponse;
    }
}
