package com.zpc.service.impl;

import com.zpc.entity.controlcenter.TaskConfig;
import com.zpc.service.TaskConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 和谐社会人人有责 on 2017/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml" , "classpath:spring/spring-dao.xml"})
public class TaskConfigServiceImplTest {

    @Autowired
    private TaskConfigService taskConfigService;

    @Test
    public void getConfig() throws Exception {
        TaskConfig taskConfig = taskConfigService.getConfig(1002);
        System.out.println(taskConfig);
    }

    @Test
    public void updateConfig() throws Exception {
        TaskConfig taskConfig = new TaskConfig(1001 , "script_str" ,
                "http://www.tencent.com" , 10 , "200,404,403,500" , "tencent_data");
        taskConfigService.updateConfig(taskConfig);
    }

}