package com.foryou.tax.api.email;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.util.StringUtils;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


/**
 * @author ：Raymon
 * @date ：Created in 2020/5/26
 * @description: 发送邮件
 */
public class EmailSender {

    private MailType mailType;
    private String userName;
    private String passWord;
    private Properties properties;


    public EmailSender(MailType mailType) {
        this.mailType = mailType;
        properties = getProperties();
    }


    public boolean sender(String receivers, String cc, String mailTitle, String mailContent, boolean isHtml, Map<String, byte[]> mapFile) throws MessagingException, IOException, javax.mail.MessagingException {

        /**
         * 1.创建session
         */
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                /**
                 * 账号密码
                 */
                return new PasswordAuthentication(userName, passWord);
            }
        });

        session.setDebug(true);

        /**
         * 2.通过session获取Transport对象（发送邮件的核心API）
         */
        Transport ts = session.getTransport("smtp");
        /**
         * 3.通过邮件用户名密码链接
         */
        ts.connect(properties.getProperty("mail.host"), userName, passWord);
        /**
         * 4.创建邮件
         */
        MimeMessage mimeMessage = new MimeMessage(session);

        /**
         * 设置发件人
         */
        mimeMessage.setFrom(new InternetAddress(userName));
        new InternetAddress();
        Address[] address = InternetAddress.parse(receivers);
        mimeMessage.setRecipients(Message.RecipientType.TO, address);

        /**
         * 设置抄送人
         */
        if (!StringUtils.isEmpty(cc)) {
            mimeMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
        }
        mimeMessage.setSubject(mailTitle);
        if (!isHtml) {
            mailContent = String.format("<pre>%s</pre>", mailContent);
        }
        /**
         * 创建多重消息
         */
        Multipart multipart = new MimeMultipart();

        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(mailContent, "text/html;charset=utf-8");
        multipart.addBodyPart(bodyPart);
        if (mapFile != null) {
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-Java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-Java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-Java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-Java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-Java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);

            for (Map.Entry<String, byte[]> map : mapFile.entrySet()) {
                BodyPart messageBodyPart = new MimeBodyPart();
                InputStream inputStream = new ByteArrayInputStream(map.getValue());
                DataSource source = new ByteArrayDataSource(inputStream, "application/txt");
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(MimeUtility.encodeText(map.getKey()));
                multipart.addBodyPart(messageBodyPart);
            }


        }

        mimeMessage.setContent(multipart);

        /**
         * 5.发送电子邮件
         */
        ts.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        return true;
    }

    private Properties getProperties() {
        if (mailType.equals(MailType.m163)) {
            Properties prop = new Properties();
            prop.put("mail.host", "smtp.163.com");
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.auth", true);
            return prop;
        }
        if (mailType.equals(MailType.qq)) {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.qq.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.smtp.socketFactory.class", "Javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            return prop;
        }
        if (mailType.equals(MailType.outlook)) {
            /**
             * 账号密码
             */
            userName = "DCFL.credit@doosan.com";
            passWord = "123qwer@";
            Properties prop = new Properties();
            prop.setProperty("mail.host", "krrelay.corp.doosan.com");
            prop.setProperty("mail.smtp.port", "25");
            prop.setProperty("mail.transport.protocol", "exchange");
            prop.setProperty("mail.smtp.auth", "true");
            return prop;
        }
        return null;
    }

    public enum MailType {
        m163, qq, outlook
    }
}
