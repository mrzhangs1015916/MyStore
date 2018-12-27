package com.netease.mystore.web.common;

import java.io.Serializable;

/**
 * 用户类型类
 * Created by switch on 16/11/14.
 */
public class User implements Serializable {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户类型
     */
    private Integer usertype;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}
