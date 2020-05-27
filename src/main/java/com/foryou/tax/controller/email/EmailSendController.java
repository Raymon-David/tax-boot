package com.foryou.tax.controller.email;

import com.foryou.tax.api.email.EmailSender;
import com.foryou.tax.util.LoggerUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：Raymon
 * @date ：Created in 2020/5/26
 * @description: send email
 */
@RestController
public class EmailSendController {

    /**
     * 发送 outlook exchange 邮件
     */
    @RequestMapping(value ="/email/sendExchange" ,method = RequestMethod.POST)
    public void sendExchangeEmail(HttpServletRequest request, HttpServletResponse response){

        String receivers = "weiguo.zhang@doosan.com";
        String cc = "";
        String mailTitle = "Test";
        String mailContent = "Hello World";
        boolean isHtml = false;

        try {
            EmailSender emailSender = new EmailSender(EmailSender.MailType.outlook);
            LoggerUtils.debug(getClass(), "receivers is: " + receivers);

            boolean sendStatus = emailSender.sender(receivers, cc, mailTitle, mailContent, isHtml, null);
            LoggerUtils.debug(getClass(), "email send status is: " + sendStatus);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
