package com.zpc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zpc.dao.controlcenter.ScheduleDao;
import com.zpc.dto.ExecuteResult;
import com.zpc.dto.OrderMessage;
import com.zpc.dto.Request;
import com.zpc.entity.controlcenter.TaskConfig;
import com.zpc.service.AlertService;
import com.zpc.service.TaskConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        String requestUrl = taskConfig.getSeedUrl();
        Request request = makeRequest(requestUrl);

        OrderMessage message = new OrderMessage();
        message.setRequest(request);
        message.setOrder(order);
        String queueName = "QueueName" + taskId;
        message.setContainerName(queueName);
        try {
            rabbitTemplate.convertAndSend("queueExecuteKey", message);
            logger.info("队列指令发送成功 " + order + "  " + message.toString());

        } catch (Exception e) {
            logger.error(e.getMessage() + order + "队列指令发送失败", e);
            return new ExecuteResult(false, order + "队列指令发送失败");
        }
        return new ExecuteResult(true, order + "指令发送成功，队列名为 : " + queueName);
    }

    public Request makeRequest(String requestUrl) {
        Request request = new Request();
        Map<String, Object> map = (Map<String, Object>) JSONObject.parse(requestUrl);
        request.setUrl((String) map.get("url"));
        request.setMethod((String) map.get("method"));
        // TODO 完善对应的信息
        return request;
    }

}
