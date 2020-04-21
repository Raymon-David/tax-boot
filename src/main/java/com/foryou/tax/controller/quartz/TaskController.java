package com.foryou.tax.controller.quartz;

import com.foryou.tax.pojo.quartz.TaskEntity;
import com.foryou.tax.process.quartz.TaskProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/21
 * @description:
 */
@RestController
public class TaskController {

    @Autowired
    private TaskProcess taskProcess;

    /**
     * 保存定时任务
     * @param request
     * @param response
     * @param taskEntity
     */
    @RequestMapping(value = "/addTask")
    public void addTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity){
        taskProcess.addTask(request, response, taskEntity);
    }

    /**
     * 开始定时任务
     * @param request
     * @param response
     * @param taskEntity
     */
    @RequestMapping(value = "/resumeTask")
    public void resumeTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity){
        taskProcess.resumeTask(request, response, taskEntity);
    }

    /**
     * 查询job
     * @param request
     * @param response
     * @param taskEntity
     */
    @RequestMapping(value = "/findTaskList")
    public void findTaskList(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity){
        taskProcess.findTaskList(request, response, taskEntity);
    }

    /**
     * 修改定时任务
     * @param request
     * @param response
     * @param taskEntity
     */
    @RequestMapping(value = "/updateTask")
    public void updateTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity){
        taskProcess.updateTask(request, response, taskEntity);
    }

    /**
     * 停止任务
     * @param request
     * @param response
     * @param taskEntity
     */
    @RequestMapping(value = "/pauseTask")
    public void pauseTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity){
        taskProcess.pauseTask(request, response, taskEntity);
    }

    /**
     * 删除任务
     * @param request
     * @param response
     * @param taskEntity
     */
    @RequestMapping(value = "/deleteTask")
    public void deleteTask(HttpServletRequest request, HttpServletResponse response, TaskEntity taskEntity){
        taskProcess.deleteTask(request, response, taskEntity);
    }
}
