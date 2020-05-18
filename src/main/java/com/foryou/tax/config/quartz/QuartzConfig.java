package com.foryou.tax.config.quartz;

import com.foryou.tax.api.quartz.DropTableTask;
import com.foryou.tax.api.quartz.InvoiceTask;
import com.foryou.tax.api.quartz.TestTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/21
 * @description: quartz config
 * 这是一个测试，每5小时执行一次，关联的task是 TestTask.class
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testQuartz() {
        return JobBuilder.newJob(TestTask.class).withIdentity("testTask").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(5)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(testQuartz())
                .withIdentity("testTask")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail invoiceQuartz(){
        return JobBuilder.newJob(InvoiceTask.class).withIdentity("invoiceTask").storeDurably().build();
    }

    @Bean
    public Trigger invoiceQuartzTrigger(){

        /**
         * 每周三凌晨0点
         */
        return TriggerBuilder.newTrigger().forJob(invoiceQuartz())
                .withIdentity("invoiceTask")
                .withSchedule(cronSchedule("0 0 0 ? * WED"))
                .build();
    }

    @Bean
    public JobDetail dropTableQuartz() {
        return JobBuilder.newJob(DropTableTask.class).withIdentity("dropTableTask").storeDurably().build();
    }

    @Bean
    public Trigger dropTableTrigger() {

        /**
         * 每个月1日凌晨0点
         */
        return TriggerBuilder.newTrigger().forJob(dropTableQuartz())
                .withIdentity("dropTableTask")
                .withSchedule(cronSchedule("0 0 0 1 * ?"))
                .build();
    }
}
