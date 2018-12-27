package com.netease.mystore.domain;

import java.io.Serializable;

/**
 * 内容实体类
 * Created by switch on 16/11/14.
 */
public class Content implements Serializable {
    /**
     * 内容ID
     */
    private Integer id;
    /**
     * 当前价格
     */
    private Long price;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String icon;
    /**
     * 摘要
     */
    private String abst;
    /**
     * 正文
     */
    private String text;

    /**
     * 交易记录
     */
    private TransactionRecord record;

    public Content() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TransactionRecord getRecord() {
        return record;
    }

    public void setRecord(TransactionRecord record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", abst='" + abst + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
