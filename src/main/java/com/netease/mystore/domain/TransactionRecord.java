package com.netease.mystore.domain;

import java.io.Serializable;

/**
 * 交易记录实体类
 * Created by switch on 16/11/14.
 */
public class TransactionRecord implements Serializable {
    /**
     * 订单ID
     */
    private Integer id;
    /**
     * 购买价格
     */
    private Integer price;
    /**
     * 购买时间
     */
    private Long time;
    /**
     * 该订单对应的人
     */
    private Person person;
    /**
     * 该订单对应的内容
     */
    private Content content;

    public TransactionRecord() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "id=" + id +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
