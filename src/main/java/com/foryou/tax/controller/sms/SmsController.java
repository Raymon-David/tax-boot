package com.foryou.tax.controller.sms;

import com.foryou.tax.api.sms.SmsSender;
import com.foryou.tax.pojo.sms.SmsPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author ：Raymon
 * @date ：Created in 2020/7/15
 * @description : 短信发送
 */
public class SmsController {

    /**
     * 发送 sms
     */
    @RequestMapping(value ="/sms/send" ,method = RequestMethod.POST)
    public void sendSms(HttpServletRequest request, HttpServletResponse response){
        SmsPojo smsPojo = new SmsPojo();
        smsPojo.setAccount("dh84781");
        smsPojo.setPassword("password");
        smsPojo.setSign("【斗山融资租赁】");
        smsPojo.setSubcode("30011");
        /**
         * 短信id，查询短信状态报告时需要，（可选）
         */
        smsPojo.setMsgid(UUID.randomUUID().toString().replace("-", ""));
        smsPojo.setSubmitUrl("http://3tong.net");
        smsPojo.setPhone("17600808516");
        smsPojo.setContent("Hello");

        SmsSender smsSender = new SmsSender();
        smsSender.send(smsPojo);
    }
}
