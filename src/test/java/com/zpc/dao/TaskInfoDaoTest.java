package com.zpc.dao;

import com.zpc.dao.controlcenter.TaskInfoDao;
import com.zpc.entity.controlcenter.TaskInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TaskInfoDaoTest {
    @Autowired
    private TaskInfoDao taskInfoDao;

    @Test
    public void findById() throws Exception {
        System.out.println(taskInfoDao.findById(1001).toString());
    }

    @Test
    public void findAll() throws Exception {
        List<TaskInfo> list = taskInfoDao.findAll();
        for (TaskInfo taskInfo : list) {
            System.err.println("taskInfo : " + taskInfo.toString());
        }
    }

    @Test
    public void findByName() throws Exception {
        List<TaskInfo> list = taskInfoDao.findByName("5");
        for (TaskInfo taskInfo : list) {
            System.err.println("taskInfo : " + taskInfo.toString());
        }
    }

    @Test
    public void create() throws Exception {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskName("DAO测试任务5");
        taskInfoDao.create(taskInfo);
    }

    @Test
    public void updateTaskInfo() throws Exception {
        taskInfoDao.updateTaskInfo("DAO测试任务0" , 1000);
    }

    @Test
    public void delete() throws Exception {
        taskInfoDao.delete(1000);
    }

    @Test
    public void updateRunTime() throws Exception {
        taskInfoDao.updateRunTime(1001 , new Date());
    }

    @Test
    public void updateRunStatus() throws Exception {
        taskInfoDao.updateRunStatus(1001 , 1);
    }

}