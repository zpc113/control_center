package com.zpc.service.impl;

import com.zpc.dao.controlcenter.TaskConfigDao;
import com.zpc.dao.datacenter.DataTableDao;
import com.zpc.entity.controlcenter.TaskConfig;
import com.zpc.service.TaskConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 和谐社会人人有责 on 2017/11/25.
 */
@Service
public class TaskConfigServiceImpl implements TaskConfigService {

    @Autowired
    private TaskConfigDao taskConfigDao;
    @Autowired
    private DataTableDao dataTableDao;

    /**
     * 获取任务配置，根据具体的业务，如果该任务还没有配置，则生成一个配置
     *
     * @param taskId
     * @return
     */
    public TaskConfig getConfig(long taskId) {
        TaskConfig taskConfig = taskConfigDao.findByTaskId(taskId);
        if (taskConfig == null) {
            taskConfig = new TaskConfig(taskId , "" , "" , 5 , "" , "");
            taskConfigDao.create(taskConfig);
        }
        return taskConfig;
    }

    /**
     * 更新任务配置信息
     * @param taskConfig
     */
    public void updateConfig(TaskConfig taskConfig) {
        String tableName = taskConfigDao.findByTaskId(taskConfig.getTaskId()).getDataTableName();
        if (!"".equals(tableName)) {
            // 新建数据表
            dataTableDao.createTable(tableName);
        }
        taskConfigDao.updateTaskConfig(taskConfig);
    }
}
