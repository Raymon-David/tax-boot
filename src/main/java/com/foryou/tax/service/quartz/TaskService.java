package com.foryou.tax.service.quartz;

import com.foryou.tax.pojo.quartz.TaskEntity;

import java.util.List;

public interface TaskService {
    List<TaskEntity> findTaskList(TaskEntity taskEntity);
}
