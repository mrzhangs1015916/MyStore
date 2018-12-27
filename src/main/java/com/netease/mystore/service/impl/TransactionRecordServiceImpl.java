package com.netease.mystore.service.impl;

import com.netease.mystore.dao.TransactionRecordDao;
import com.netease.mystore.domain.TransactionRecord;
import com.netease.mystore.service.TransactionRecordService;

import javax.annotation.Resource;

/**
 * 订单记录服务类
 * Created by switch on 16/11/21.
 */
public class TransactionRecordServiceImpl implements TransactionRecordService{
    /**
     * 注入订单记录持久层Dao
     */
    @Resource
    private TransactionRecordDao transactionRecordDao;

    @Override
    public Integer save(TransactionRecord record) {
        return transactionRecordDao.save(record);
    }
}
