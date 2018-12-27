package com.netease.mystore.service.impl;

import com.netease.mystore.dao.ContentDao;
import com.netease.mystore.domain.Content;
import com.netease.mystore.service.ContentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容服务接口实现类
 * Created by switch on 16/11/14.
 */
public class ContentServiceImpl implements ContentService {
    /**
     * 注入内容持久层对象
     */
    @Resource
    private ContentDao contentDao;

    @Override
    public List<Content> findIsBuy() {
        return contentDao.findIsBuy();
    }

    @Override
    public List<Content> findIsNotBuy() {
        return contentDao.findIsNotBuy();
    }

    @Override
    public Content findById(String id) {
        return contentDao.findById(id);
    }

    @Override
    public Integer findContentNum() {
        return contentDao.findContentNum();
    }

    @Override
    public Integer save(Content content) {
        return contentDao.save(content);
    }

    @Override
    public Integer update(Content content) {
        return contentDao.update(content);
    }

    @Override
    public Integer deleteById(String id) {
        return contentDao.deleteById(id);
    }


}
