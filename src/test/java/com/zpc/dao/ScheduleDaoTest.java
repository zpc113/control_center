package com.zpc.dao;

import com.zpc.dao.controlcenter.ScheduleDao;
import com.zpc.entity.controlcenter.Schedule;
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
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ScheduleDaoTest {

    @Autowired
    private ScheduleDao scheduleDao;

    @Test
    public void findAll() throws Exception {
        List<Schedule> schedules = scheduleDao.findAll(1001);
        for (Schedule schedule : schedules) {
            System.out.println("schedule-------------->" + schedule.toString());
        }
    }

    @Test
    public void setEnd() throws Exception {
        scheduleDao.setEnd(new Date() , 4);
    }

    @Test
    public void create() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setTaskId(1001);
        schedule.setStartTime(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dt = sdf.format(new Date());
        schedule.setDt(dt);

        long scheduleId = scheduleDao.create(schedule);
        System.out.println("----------------------------->" + schedule.getScheduleId());
    }

}