package com.zpc.service;

import com.zpc.dto.ExecuteResult;

/**
 * Created by 和谐社会人人有责 on 2017/11/28.
 */
public interface AlertService {

    ExecuteResult sendMessage(long taskId , String order);

}
