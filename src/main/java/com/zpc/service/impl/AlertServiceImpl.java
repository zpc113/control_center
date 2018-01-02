package com.zpc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zpc.dao.controlcenter.ScheduleDao;
import com.zpc.dto.*;
import com.zpc.entity.controlcenter.TaskConfig;
import com.zpc.service.AlertService;
import com.zpc.service.TaskConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by 和谐社会人人有责 on 2017/11/28.
 */
@Service
public class AlertServiceImpl implements AlertService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TaskConfigService taskConfigService;
    @Autowired
    private ScheduleDao scheduleDao;

    /**
     * 运行任务
     *
     * @param taskId
     * @return
     */
    public ExecuteResult sendMessage(long taskId, String order) {
        // 向队列服务器发送新建队列指令
        TaskConfig taskConfig = taskConfigService.getConfig(taskId);
        Request request = makeRequest(taskConfig);

        OrderMessage message = new OrderMessage();
        message.setRequest(request);
        message.setOrder(order);
        String queueName = "QueueName" + taskId;
        message.setContainerName(queueName);
        try {
            rabbitTemplate.convertAndSend(RoutingKey.QUEUE_ROUTINGKEY , message);
            logger.info("队列指令发送成功 " + order + "  " + message.toString());

        } catch (Exception e) {
            logger.error(e.getMessage() + order + "队列指令发送失败", e);
            return new ExecuteResult(false, order + "队列指令发送失败");
        }
        return new ExecuteResult(true, order + "指令发送成功，队列名为 : " + queueName);
    }

    /**
     * 暂停任务
     * @param taskId
     * @param order
     * @return
     */
    public ExecuteResult sendMessageSuspend(long taskId , long scheduleId ,  String order) {
        String containerName = "QueueName" + taskId + "_scheduleId_" + scheduleId;
        OrderMessage orderMessageSuspend = new OrderMessage();
        orderMessageSuspend.setContainerName(containerName);
        orderMessageSuspend.setOrder(ControlExecutorOrder.SUSPEND);
        boolean b = true;
        String message = "";
        // 发送消息到下载服务器
        try {
            rabbitTemplate.convertAndSend(RoutingKey.DOWNSERVICE_ROUTINGKEY , orderMessageSuspend);
            logger.info("暂停下载服务消息发送成功！ -----> " + containerName);
        } catch (Exception e) {
            b = false;
            message = "暂停下载服务消息发送失败 ----> " + containerName;
            logger.error(e.getMessage() , message);
        }
        // 发送消息到解析服务器
        try {
            rabbitTemplate.convertAndSend(RoutingKey.PARSESERVICE_ROUTINGKEY , orderMessageSuspend);
            logger.info("暂停解析服务消息发送成功！ -----> " + containerName);
        } catch (Exception e) {
            b = false;
            message = message + ";暂停解析服务消息发送失败 ----> " + containerName;
            logger.error("暂停解析服务消息发送失败 -----> " + containerName);
        }
        ExecuteResult executeResult = new ExecuteResult();
        if (!b) {
            executeResult.setMessage(message);
            executeResult.setSuccess(b);
            return executeResult;
        }
        executeResult.setMessage("消息发送成功！");
        executeResult.setSuccess(b);
        return executeResult;
    }

    public Request makeRequest(TaskConfig taskConfig) {
        String requestUrl = taskConfig.getSeedUrl();
        Request request = new Request();
        Map<String, Object> map = (Map<String, Object>) JSONObject.parse(requestUrl);
        request.setUrl((String) map.get("url"));
        request.setMethod((String) map.get("method"));
        if (taskConfig.getUserAgent() == 0) {
            request.setMobile(true);
        } else {
            request.setMobile(false);
        }
        // TODO 完善对应的信息
        return request;
    }

    /**
     * 停止任务
     * @param taskId
     * @param scheduleId
     * @param order
     * @return
     */
    public ExecuteResult sendMessageStop(long taskId, long scheduleId, String order) {
        String containerName = "QueueName" + taskId + "_scheduleId_" + scheduleId;
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setOrder(order);
        orderMessage.setContainerName(containerName);
        try {
            rabbitTemplate.convertAndSend(RoutingKey.DOWNSERVICE_ROUTINGKEY , orderMessage);
            logger.info("销毁下载线程池请求发送成功" + orderMessage.toString());
            return new ExecuteResult(true , null);
        } catch (Exception e) {
            logger.error(e.getMessage() , "销毁下载线程池请求发送失败" + orderMessage.toString());
        }

        return new ExecuteResult(false , null);
    }

    /**
     * 恢复任务
     * @param taskId
     * @param scheduleId
     * @param order
     * @return
     */
    public ExecuteResult sendMessageRecover(long taskId, long scheduleId, String order) {
        String containerName = "QueueName" + taskId + "_scheduleId_" + scheduleId;
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setOrder(order);
        orderMessage.setContainerName(containerName);
        try {
            rabbitTemplate.convertAndSend(RoutingKey.DOWNSERVICE_ROUTINGKEY , orderMessage);
            logger.info("恢复下载线程池请求发送成功" + orderMessage.toString());
            return new ExecuteResult(true , null);
        } catch (Exception e) {
            logger.error(e.getMessage() , "销毁下载线程池请求发送失败" + orderMessage.toString());
        }

        return new ExecuteResult(false , null);
    }


}
