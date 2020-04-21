package com.foryou.tax.process.quartz;

import com.foryou.tax.pojo.quartz.TaskEntity;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.quartz.TaskService;
import com.foryou.tax.util.DateUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/21
 * @description:
 */
@Service
public class TaskProcess extends BaseProcess {

    @Autowired
    private TaskService taskService;

    @Autowired
    private Scheduler scheduler;


    /**
     * @param taskEntity
     * @return
     * @// TODO: 2018/6/8 保存定时任务
     */
    @SuppressWarnings("unchecked")
    public void addTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity) {
        String jobName = taskEntity.getJobName(),
                jobGroup = taskEntity.getJobGroup(),
                cronExpression = taskEntity.getCronExpression(),
                jobDescription = taskEntity.getJobDescription();
        try {
            if (checkExists(jobName, jobGroup)) {
                writeClientJson(response, String.format("Job已经存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup), null);
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(jobDescription).withSchedule(scheduleBuilder).build();

            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(taskEntity.getJobClass());
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
            writeClientJson(response, true, null);
        } catch (SchedulerException | ClassNotFoundException e) {
            writeClientJson(response, "类名不存在或执行表达式错误", null);
            e.printStackTrace();
        }
    }

    /**
     * @param taskEntity
     * @return
     * @// TODO: 2018/6/8 开始定时任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void resumeTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity) {
        try {
            scheduler.resumeJob(JobKey.jobKey(taskEntity.getJobName(), taskEntity.getJobGroup()));
            writeClientJson(response, true, null);
        } catch (Exception e) {
            writeClientJson(response, "Failed", null);
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @// TODO: 2018/6/5  查询job
     */
    @Transactional(readOnly = true)
    public void findTaskList(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity) {
        List<TaskEntity> list = taskService.findTaskList(taskEntity);
        writeClientJson(response, list, null);
    }

    /**
     * 修改定时任务
     *
     * @param info
     */
    public void updateTask(HttpServletRequest request, HttpServletResponse response, TaskEntity info) {
        String jobName = info.getJobName(),
                jobGroup = info.getJobGroup(),
                cronExpression = info.getCronExpression(),
                jobDescription = info.getJobDescription(),
                createTime =  DateUtil.getNow("yyyy-MM-dd HH:mm:ss");
        try {
            if (!checkExists(jobName, jobGroup)) {
                writeClientJson(response, String.format("Job不存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup), null);
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(cronScheduleBuilder).build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
            writeClientJson(response, true, null);
        } catch (SchedulerException e) {
            writeClientJson(response, "类名不存在或执行表达式错误", null);
            e.printStackTrace();
        }
    }

    /**
     * @param taskEntity
     * @// TODO: 2018/6/1 停止任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void pauseTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity) {
        TriggerKey triggerKey = TriggerKey.triggerKey(taskEntity.getJobName(), taskEntity.getJobGroup());
        try {
            if (checkExists(taskEntity.getJobName(), taskEntity.getJobGroup())) {
                /**
                 * 停止触发器
                 */
                scheduler.pauseTrigger(triggerKey);
            }
            writeClientJson(response, true, null);
        } catch (Exception e) {
            writeClientJson(response, e.getMessage(), null);
            e.printStackTrace();
        }
    }

    /**
     * @param taskEntity
     * @return
     * @// TODO: 2018/6/7 删除任务
     */
    public Boolean deleteTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity) {
        TriggerKey triggerKey = TriggerKey.triggerKey(taskEntity.getJobName(), taskEntity.getJobGroup());
        try {
            if (checkExists(taskEntity.getJobName(), taskEntity.getJobGroup())) {
                /**
                 * 停止触发器
                 */
                scheduler.pauseTrigger(triggerKey);
                /**
                 * 移除触发器
                 */
                scheduler.unscheduleJob(triggerKey);
                return true;
            }
        } catch (SchedulerException e) {
            writeClientJson(response, e.getMessage(), null);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证是否存在
     *
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}
