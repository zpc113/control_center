package com.zpc.web;

import com.zpc.entity.controlcenter.TaskInfo;
import com.zpc.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by 和谐社会人人有责 on 2017/11/23.
 */
@Controller
@RequestMapping("/taskInfo")
public class TaskInfoController {

    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * list页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String taskList(Model model) {
        List<TaskInfo> taskInfoList = taskInfoService.findAll();
        model.addAttribute("taskInfoList" , taskInfoList);
        return "/task/list";
    }

    /**
     * 更新任务名称
     * @param taskId
     * @param taskName
     * @return
     */
    @RequestMapping(value = "/{taskId}/update" , method = RequestMethod.POST)
    public String updateTaskName(@PathVariable("taskId") long taskId , String taskName) {
        taskInfoService.updateTaskName(taskName , taskId);
        return "redirect:/taskInfo/list";
    }

    /**
     * 新建任务
     * @param taskName
     * @return
     */
    @RequestMapping(value = "/createTask" , method = RequestMethod.POST)
    public String createTask(String taskName) {
        taskInfoService.createTask(taskName);
        return "redirect:/taskInfo/list";
    }

    /**
     * 删除任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/delete/{taskId}" , method = RequestMethod.GET)
    public String deleteTask(@PathVariable("taskId") long taskId) {
        taskInfoService.delete(taskId);
        return "redirect:/taskInfo/list";
    }

}
