package com.zpc.service;

import com.zpc.dto.ExecuteResult;

/**
 * Created by 和谐社会人人有责 on 2017/11/28.
 */
public interface AlertService {

    ExecuteResult sendMessage(long taskId , String order);

    ExecuteResult sendMessageSuspend(long taskId , long scheduleId , String order);

    ExecuteResult sendMessageStop(long taskId , long scheduleId , String order);

    ExecuteResult sendMessageRecover(long taskId , long scheduleId , String order);
}
