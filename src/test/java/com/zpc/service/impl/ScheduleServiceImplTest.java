package com.zpc.service.impl;

import com.zpc.entity.controlcenter.Schedule;
import com.zpc.service.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml" , "classpath:spring/spring-dao.xml","classpath:spring/spring-rabbitMQ.xml"})
public class ScheduleServiceImplTest {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void findAll() throws Exception {
        List<Schedule> schedules = scheduleService.findAll(1001);
        for (Schedule schedule : schedules) {
            System.out.println(schedule.toString());
        }
    }

    @Test
    public void create() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setTaskId(1001);
        schedule.setStartTime(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dt = sdf.format(new Date());
        schedule.setDt(dt);

        scheduleService.create(schedule);
    }

    @Test
    public void setEnd() throws Exception {
        scheduleService.setEnd(new Date() , 9);
    }

}