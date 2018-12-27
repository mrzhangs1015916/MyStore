package com.netease.mystore.service;

import com.netease.mystore.domain.TransactionRecord;

/**
 * 订单记录服务接口类
 * Created by switch on 16/11/21.
 */
public interface TransactionRecordService {
    /**
     * 保存订单记录
     *
     * @param record
     * @return
     */
    Integer save(TransactionRecord record);
}
