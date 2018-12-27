package com.netease.mystore.dao;

import com.netease.mystore.domain.TransactionRecord;

/**
 * 订单记录持久层接口
 * Created by switch on 16/11/21.
 */
public interface TransactionRecordDao {
    /**
     * 保存订单记录
     *
     * @param record
     * @return
     */
    Integer save(TransactionRecord record);
}
