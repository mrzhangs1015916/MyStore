package com.netease.mystore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人实体类
 * Created by switch on 16/11/14.
 */
public class Person implements Serializable {
    /**
     * 买家
     */
    public final static Integer TYPE_BUYER = 0;
    /**
     * 卖家
     */
    public final static Integer TYPE_SELLER = 1;
    /**
     * 个人ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码md5加密
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 类型，买家0，卖家1
     */
    private Integer userType;

    /**
     * 交易记录集合，对应于交易记录表的集合，一对多关系
     */
    private List<TransactionRecord> records = new ArrayList<>(0);

    public Person() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public List<TransactionRecord> getRecords() {
        return records;
    }

    public void setRecords(List<TransactionRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userType=" + userType +
                '}';
    }
}
