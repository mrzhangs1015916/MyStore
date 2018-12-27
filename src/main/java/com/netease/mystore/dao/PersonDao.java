package com.netease.mystore.dao;

import com.netease.mystore.domain.Person;

/**
 * 用户持久层接口
 * Created by switch on 16/11/14.
 */
public interface PersonDao {

    /**
     * 用户登录
     *
     * @param person
     * @return
     */
    Person login(Person person);
}
