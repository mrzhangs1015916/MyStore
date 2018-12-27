package com.netease.mystore.service.impl;

import com.netease.mystore.dao.ContentDao;
import com.netease.mystore.dao.PersonDao;
import com.netease.mystore.domain.Content;
import com.netease.mystore.domain.Person;
import com.netease.mystore.service.PersonService;
import com.netease.mystore.web.common.Product;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务接口实现类
 * Created by switch on 16/11/14.
 */
public class PersonServiceImpl implements PersonService {
    /**
     * 注入个人持久层对象
     */
    @Resource
    private PersonDao personDao;

    @Resource
    private ContentDao contentDao;

    @Override
    public Person login(Person person) {
        return personDao.login(person);
    }

    @Override
    public List<Product> getBuyProductList() {
        List<Content> contents = contentDao.findIsBuy();
        List<Product> products = new ArrayList<>();
        for (Content content : contents) {
            Product product = new Product();
            product.setId(content.getId());
            product.setTitle(content.getTitle());
            product.setImage(content.getIcon());
            product.setBuyPrice(new BigDecimal(content.getRecord().getPrice() / 100.0).setScale(2,BigDecimal.ROUND_HALF_UP));
            product.setBuyTime(content.getRecord().getTime());
            products.add(product);
        }
        return products;
    }
}
