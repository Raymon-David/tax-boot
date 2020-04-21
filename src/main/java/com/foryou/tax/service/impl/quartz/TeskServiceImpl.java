package com.foryou.tax.service.impl.quartz;

import com.foryou.tax.dao.quartz.TaskMapper;
import com.foryou.tax.pojo.quartz.TaskEntity;
import com.foryou.tax.service.quartz.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/21
 * @description:
 */
@Service
public class TeskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public List<TaskEntity> findTaskList(TaskEntity taskEntity) {
        return taskMapper.findTaskList(taskEntity);
    }
}
