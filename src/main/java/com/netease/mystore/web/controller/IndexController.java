package com.netease.mystore.web.controller;

import com.netease.mystore.domain.Content;
import com.netease.mystore.service.ContentService;
import com.netease.mystore.web.common.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页控制器
 * Created by switch on 16/11/13.
 */
@Controller
public class IndexController {

    /**
     * 注入内容服务Bean
     */
    @Resource
    private ContentService contentService;

    /**
     * 显示主界面
     *
     * @param request
     * @return
     */
    @RequestMapping("/")
    public String showMainUI(HttpServletRequest request) {
        // 获取已经购买的商品集合
        List<Content> isBuy = contentService.findIsBuy();
        // 获取未购买的商品集合
        List<Content> isNotBuy = contentService.findIsNotBuy();

        // 产品列表
        List<Product> productList = new ArrayList<>();
        for (Content content : isBuy) {
            // 封装已购买商品集合
            Product product = new Product();
            encapsulateSameProduct(content, product);
            product.setIsBuy(true);
            product.setIsSell(true);
            productList.add(product);
        }

        for (Content content : isNotBuy) {
            // 封装未购买商品
            Product product = new Product();
            encapsulateSameProduct(content, product);
            product.setIsBuy(false);
            product.setIsSell(false);
            productList.add(product);
        }

        // 将商品列表加入request域中
        request.setAttribute("productList", productList);

        // 跳转到主页
        return "index";
    }

    /**
     * 封装商品通用数据
     *
     * @param content
     * @param product
     */
    private void encapsulateSameProduct(Content content, Product product) {
        product.setId(content.getId());
        product.setTitle(content.getTitle());
        product.setImage(content.getIcon());
        product.setPrice(new BigDecimal(content.getPrice() / 100.0).setScale(2,BigDecimal.ROUND_HALF_UP));
    }
}
