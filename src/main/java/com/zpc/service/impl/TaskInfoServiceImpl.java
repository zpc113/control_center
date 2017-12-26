package com.zpc.service.impl;

import com.zpc.dao.controlcenter.TaskInfoDao;
import com.zpc.entity.controlcenter.TaskInfo;
import com.zpc.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/11/23.
 */
@Service
public class TaskInfoServiceImpl implements TaskInfoService {
    @Autowired
    private TaskInfoDao taskInfoDao;

    public List<TaskInfo> findAll() {
        return taskInfoDao.findAll();
    }

    public TaskInfo findById(long taskId) {
        return taskInfoDao.findById(taskId);
    }

    public List<TaskInfo> findByName(String taskName) {
        return taskInfoDao.findByName(taskName);
    }

    public void createTask(String taskName) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskName(taskName);
        taskInfoDao.create(taskInfo);
    }

    public void updateTaskName(String name, long id) {
        taskInfoDao.updateTaskInfo(name , id);
    }

    public void updateRunTime(Date date, long id) {
        taskInfoDao.updateRunTime(id , date);
    }

    public void updateRunStatus(int runStatus, long id) {
        taskInfoDao.updateRunStatus(id , runStatus);
    }

    public void delete(long taskId) {
        taskInfoDao.delete(taskId);
    }
}
