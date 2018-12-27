package com.netease.mystore.web.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品展示类
 * Created by switch on 16/11/14.
 */
public class Product implements Serializable {
    /**
     * 产品ID
     */
    private Integer id;
    /**
     * 产品标题
     */
    private String title;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 全文
     */
    private String detail;
    /**
     * 图片地址
     */
    private String image;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 购买时价格
     */
    private BigDecimal buyPrice;
    /**
     * 购买时间
     */
    private Long buyTime;
    /**
     * 当前用户是否购买
     */
    private Boolean isBuy;
    /**
     * 是否已经卖出
     */
    private Boolean isSell;

    public Product() {

    }

    public Long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Long buyTime) {
        this.buyTime = buyTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Boolean isBuy) {
        this.isBuy = isBuy;
    }

    public Boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(Boolean isSell) {
        this.isSell = isSell;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", buyPrice=" + buyPrice +
                ", isBuy=" + isBuy +
                ", isSell=" + isSell +
                '}';
    }
}
