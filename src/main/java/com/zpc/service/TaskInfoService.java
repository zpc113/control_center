package com.zpc.service;

import com.zpc.entity.controlcenter.TaskInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/11/23.
 */
public interface TaskInfoService {

    /**
     * 查询所有
     * @return
     */
    List<TaskInfo> findAll();

    /**
     * 根据id查找
     * @param taskId
     * @return
     */
    TaskInfo findById(long taskId);

    /**
     * 根据任务名称模糊查找
     * @param taskName
     * @return
     */
    List<TaskInfo> findByName(String taskName);

    /**
     * 新建task
     * @param >
     */
    void createTask(String taskName);

    /**
     * 修改taskName
     * @param taskName
     * @param id
     */
    void updateTaskName(String taskName , long id);

    /**
     * 修改运行时间
     * @param runTime
     * @param id
     */
    void updateRunTime(Date runTime , long id);

    /**
     * 修改运行状态
     * @param runStatus
     * @param id
     */
    void updateRunStatus(int runStatus , long id);

    void delete(long taskId);
}
