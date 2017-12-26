package com.zpc.dao;

import com.zpc.dao.controlcenter.TaskConfigDao;
import com.zpc.entity.controlcenter.TaskConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 和谐社会人人有责 on 2017/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TaskConfigDaoTest {
    @Autowired
    private TaskConfigDao taskConfigDAO;

    @Test
    public void create() throws Exception {
        TaskConfig taskConfig = new TaskConfig(1001 , "script_str" ,
                "http://www.baidu.com" , 5 , "200,404" , "baidu_data");
        taskConfigDAO.create(taskConfig);
    }

    @Test
    public void updateTaskConfig() throws Exception {
        TaskConfig taskConfig = new TaskConfig(1001 , "script_str" ,
                "http://www.google.com" , 5 , "200,404,403" , "baidu_data");
        taskConfigDAO.updateTaskConfig(taskConfig);
    }

    @Test
    public void findByTaskId() throws Exception {
        System.out.println(taskConfigDAO.findByTaskId(1001));
    }

}