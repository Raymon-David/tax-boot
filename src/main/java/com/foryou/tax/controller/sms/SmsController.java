package com.foryou.tax.controller.sms;

import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.foryou.tax.api.sms.SmsSender;
import com.foryou.tax.pojo.sms.SmsPojo;
import com.foryou.tax.util.LoggerUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ：Raymon
 * @date ：Created in 2020/7/15
 * @description : 短信发送
 */
@RestController
public class SmsController {

    /**
     * 发送 sms
     */
    @RequestMapping(value ="/sms/send" ,method = RequestMethod.POST)
    public void sendSms(HttpServletRequest request, HttpServletResponse response){

        List list = new ArrayList();
        list.add("17600808516");
        list.add("18910908002");
        list.add("18515328619");

        LoggerUtils.debug(getClass(), "短信发送链表：" + list);

        for (int i = 0; i < list.size(); i++){
            SmsPojo smsPojo = new SmsPojo();
            smsPojo.setAccount("dh84781");
            smsPojo.setPassword("3q943pUN");
            smsPojo.setSign("【斗山融资租赁】");
            smsPojo.setSubcode("30011");
            /**
             * 短信id，查询短信状态报告时需要，（可选）
             */
            smsPojo.setMsgid(UUID.randomUUID().toString().replace("-", ""));
            smsPojo.setSubmitUrl("http://3tong.net");

            LoggerUtils.debug(getClass(), "手机号是：" + list.get(i).toString());
            smsPojo.setPhone(list.get(i).toString());

            /**
             * 生成6位随机码，包括数字和字母，字母为大写
             */
            RandomGenerator randomGenerator = new RandomGenerator(6);
            System.out.println(randomGenerator.generate().toUpperCase());

            /**
             * 生成6为随机数
             */
            int[] ints = NumberUtil.generateRandomNumber(0, 9, 6);
            int num = ints[0] * 100000 + ints[1] * 10000 + ints[2] * 1000 + ints[3] * 100 + ints[4] * 10 + ints[5] ;
            LoggerUtils.debug(getClass(), "验证码是：" + num);

            smsPojo.setContent("您好，您的验证码是：" + num);

            SmsSender smsSender = new SmsSender();
            String smsResponse = smsSender.send(smsPojo);
            System.out.println(smsResponse);
            LoggerUtils.debug(getClass(), smsResponse);
        }

    }
}
