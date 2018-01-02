package com.zpc.web;

import com.zpc.dto.ControlExecutorOrder;
import com.zpc.dto.TaskOrder;
import com.zpc.dto.AjaxResult;
import com.zpc.dto.ExecuteResult;
import com.zpc.entity.controlcenter.TaskConfig;
import com.zpc.entity.controlcenter.TaskInfo;
import com.zpc.service.AlertService;
import com.zpc.service.TaskConfigService;
import com.zpc.service.TaskInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by 和谐社会人人有责 on 2017/11/25.
 */
@Controller
@RequestMapping("/config")
public class TaskConfigController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskConfigService taskConfigService;
    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private AlertService alertService;

    /**
     * 获取任务配置
     * @param taskId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{taskId}/config" , method = RequestMethod.GET)
    public String getConfig(@PathVariable("taskId") long taskId , Model model) {
        TaskConfig taskConfig = taskConfigService.getConfig(taskId);
        TaskInfo taskInfo = taskInfoService.findById(taskId);
        model.addAttribute("taskConfig" , taskConfig);
        model.addAttribute("taskInfo" , taskInfo);
        return "/task/config";
    }

    /**
     * 修改任务配置
     * @param taskConfig
     * @return
     */
    @RequestMapping(value = "/updateConfig" , method = RequestMethod.POST)
    public String updateConfig(TaskConfig taskConfig) {
        taskConfigService.updateConfig(taskConfig);
        return "redirect:/config/" + taskConfig.getTaskId() + "/config";
    }

    /**
     * 运行任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/{taskId}/run" ,
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public AjaxResult run(@PathVariable("taskId") long taskId) {
        ExecuteResult executeResult = alertService.sendMessage(taskId , TaskOrder.RUN);
        if (executeResult.isSuccess()){
            return new AjaxResult(true);
        }
        return new AjaxResult(false);
    }

    /**
     * 暂停任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/{taskId}/{scheduldId}/suspend" , method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public AjaxResult suspend(@PathVariable("taskId") long taskId , @PathVariable("scheduleId") long scheduleId) {
        ExecuteResult executeResult = alertService.sendMessageSuspend(taskId , scheduleId ,  TaskOrder.SUSPEND);
        if (executeResult.isSuccess()){
            return new AjaxResult(true);
        }
        return new AjaxResult(false);
    }

    /**
     * 暂停任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/{taskId}/{scheduldId}/recover" , method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public AjaxResult recover(@PathVariable("taskId") long taskId , @PathVariable("scheduleId") long scheduleId) {
        ExecuteResult executeResult = alertService.sendMessageRecover(taskId , scheduleId ,  ControlExecutorOrder.NEW);
        if (executeResult.isSuccess()){
            return new AjaxResult(true);
        }
        return new AjaxResult(false);
    }

    /**
     * 停止任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/{taskId}/{scheduldId}/stop" , method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public AjaxResult stop(@PathVariable("taskId") long taskId , @PathVariable("scheduleId") long scheduleId) {
        ExecuteResult executeResult = alertService.sendMessageStop(taskId , scheduleId , ControlExecutorOrder.DESTROY);
        if (executeResult.isSuccess()){
            return new AjaxResult(true);
        }
        return new AjaxResult(false);
    }

}
