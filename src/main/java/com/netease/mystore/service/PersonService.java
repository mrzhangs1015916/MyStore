package com.netease.mystore.service;

import com.netease.mystore.domain.Person;
import com.netease.mystore.web.common.Product;

import java.util.List;

/**
 * 用户服务接口
 * Created by switch on 16/11/14.
 */
public interface PersonService {
    /**
     * 用户登陆
     *
     * @param person
     * @return
     */
    Person login(Person person);

    /**
     * 获取购买产品列表
     *
     * @return
     */
    List<Product> getBuyProductList();
}
