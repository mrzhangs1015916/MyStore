package com.netease.mystore.web.controller;

import com.netease.mystore.domain.Content;
import com.netease.mystore.domain.Person;
import com.netease.mystore.domain.TransactionRecord;
import com.netease.mystore.service.ContentService;
import com.netease.mystore.service.TransactionRecordService;
import com.netease.mystore.web.common.JSONResult;
import com.netease.mystore.web.common.Product;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 内容控制器
 * Created by switch on 16/11/17.
 */
@Controller
public class ContentController {
    /**
     * 注入内容服务Bean
     */
    @Resource
    ContentService contentService;

    /**
     * 注入订单服务Bean
     */
    @Resource
    TransactionRecordService transactionRecordService;

    /**
     * 显示产品内容
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/show")
    public String showProductUI(@RequestParam(value = "id", required = false) String id, ModelMap map) {
        // 检查信息并封装数据到Product中
        if (!checkInfoAndPackageProduct(id, map))
            return "redirect:/";
        return "show";
    }

    /**
     * 检查信息并封装数据到Product中
     *
     * @param id
     * @param map
     * @return
     */
    private boolean checkInfoAndPackageProduct(String id, ModelMap map) {
        // id为null或者空字符串，显示主界面
        if (StringUtils.isBlank(id)) {
            return false;
        }
        // 根据产品ID查询商品信息，其关联了订单信息
        // 如果有对应订单，则已经封装到record中
        // 如果没有对应订单，则record为null
        Content content = contentService.findById(id);

        // 产品不存在
        if (content == null) {
            return false;
        }

        // 封装展示数据
        Product product = new Product();
        product.setId(content.getId());
        product.setTitle(content.getTitle());
        product.setSummary(content.getAbst());
        product.setDetail(content.getText());
        product.setImage(content.getIcon());
        product.setPrice(new BigDecimal(content.getPrice() / 100.0).setScale(2,BigDecimal.ROUND_HALF_UP));
        if (content.getRecord() != null) {
            product.setBuyPrice(new BigDecimal(content.getRecord().getPrice() / 100.0).setScale(2,BigDecimal.ROUND_HALF_UP));
            product.setIsBuy(true);
            product.setIsSell(true);
        } else {
            product.setBuyPrice(null);
            product.setIsBuy(false);
            product.setIsSell(false);
        }

        map.addAttribute("product", product);
        return true;
    }


    /**
     * 显示产品发布页面
     *
     * @return
     */
    @RequestMapping("/public")
    public String showProductPublicUI() {
        return "public";
    }

    /**
     * 提交发布内容
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/publicSubmit")
    public String productPublic(HttpServletRequest request, ModelMap map) {
        // 内容总数验证
        Integer contentNum = contentService.findContentNum();
        if (contentNum > 1000) {
            return "publicSubmit";
        }
        // 验证信息并包装内容，使用反射方式，调用服务类中的方法
        checkInfoAndPackageContent(request, map, "save");
        return "publicSubmit";
    }

    /**
     * 验证信息并包装内容，使用反射方式，调用服务类中的方法
     *
     * @param request
     * @param map        视图
     * @param methodName 方法名
     */
    private void checkInfoAndPackageContent(HttpServletRequest request, ModelMap map, String methodName) {
        try {
            Product product = new Product();

            // 封装request域的数据
            BeanUtils.populate(product, request.getParameterMap());

            // 进行内容限制验证
            // 标题验证
            if (StringUtils.isBlank(product.getTitle()) || product.getTitle().length() < 2 || product.getTitle().length() > 80) {
                return;
            }

            // 摘要验证
            if (StringUtils.isBlank(product.getSummary()) || product.getSummary().length() < 2 || product.getSummary().length() > 140) {
                return;
            }

            // 正文验证
            if (StringUtils.isBlank(product.getDetail()) || product.getDetail().length() < 2 || product.getSummary().length() > 1000) {
                return;
            }

            // 价格验证
            if (product.getPrice().doubleValue() < 0) {
                return;
            }


            // 封装数据
            Content content = new Content();
            content.setId(product.getId());
            content.setTitle(product.getTitle());
            content.setAbst(product.getSummary());
            content.setText(product.getDetail());
            content.setIcon(product.getImage());
            content.setPrice((product.getPrice().multiply(new BigDecimal(100))).longValue());
            // 执行操作
            // 反射执行相应业务层方法
            Method method = ContentService.class.getMethod(methodName, Content.class);
            Integer isSave = (Integer) method.invoke(contentService, content);
            if (isSave == 1) {
                // 将生成的id，重新封装到product中
                product.setId(content.getId());
                map.addAttribute("product", product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示编辑界面
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/edit")
    public String showProductEditUI(@RequestParam(value = "id", required = false) String id, ModelMap map) {
        // 检查信息并封装数据到Product中
        if (!checkInfoAndPackageProduct(id, map))
            return "redirect:/show";
        return "edit";
    }

    /**
     * 编辑商品
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/editSubmit")
    public String productEdit(HttpServletRequest request, ModelMap map) {
        // 首先判断是否具有ID
        String id = request.getParameter("id");
        // ID为空
        if (StringUtils.isBlank(id)) {
            return "editSubmit";
        }
        // 查询该id是否有对应数据
        Content content = contentService.findById(id);
        if (content == null) {
            return "editSubmit";
        }
        // 验证信息并包装内容，使用反射方式，调用服务类中的方法
        checkInfoAndPackageContent(request, map, "update");
        return "editSubmit";
    }

    /**
     * 删除商品，异步接口
     *
     * @param id
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/api/delete")
    public void deleteProduct(@RequestParam(value = "id", required = false) String id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 创建JSON响应结果对象
        JSONResult result = new JSONResult();

        // ID为空
        if (StringUtils.isBlank(id)) {
            // 设置异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "删除失败", JSONResult.ERROR_RESULT);
            // 将响应结果对象转换为字符串并响应给浏览器
            response.getWriter().write(JSONObject.fromObject(result).toString());
            return;
        }

        // 从session中获取用户信息
        Person loginPerson = (Person) request.getSession().getAttribute("loginPerson");
        // 用户不存在，或者不是卖家，则不允许删除
        if (loginPerson == null || loginPerson.getUserType() != Person.TYPE_SELLER) {
            // 设置异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "删除失败", JSONResult.ERROR_RESULT);
            // 将响应结果对象转换为字符串并响应给浏览器
            response.getWriter().write(JSONObject.fromObject(result).toString());
            return;
        }

        // 数据库中删除
        Integer res = contentService.deleteById(id);
        // 删除失败 qw
        if (res == 0) {
            // 设置异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "删除失败", JSONResult.ERROR_RESULT);
        } else {
            // 删除成功
            JSONResult.setStatusCode(result, JSONResult.SUCCESS_CODE, JSONResult.SUCCESS_MESSAGE, JSONResult.SUCCESS_RESULT);
        }

        // 将响应结果对象转换为字符串并响应给浏览器
        response.getWriter().write(JSONObject.fromObject(result).toString());
    }

    /**
     * 购买商品，异步接口
     *
     * @param id
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/api/buy")
    public void buyProduct(@RequestParam(value = "id", required = false) String id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 创建JSON响应结果对象
        JSONResult result = new JSONResult();

        // ID为空
        if (StringUtils.isBlank(id)) {
            // 设置异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "购买失败", JSONResult.ERROR_RESULT);
            // 将响应结果对象转换为字符串并响应给浏览器
            response.getWriter().write(JSONObject.fromObject(result).toString());
            return;
        }

        // 从session中获取用户信息
        Person loginPerson = (Person) request.getSession().getAttribute("loginPerson");
        // 用户不存在，或者不是买家，则不允许购买
        if (loginPerson == null || loginPerson.getUserType() != Person.TYPE_BUYER) {
            // 设置异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "购买失败", JSONResult.ERROR_RESULT);
            // 将响应结果对象转换为字符串并响应给浏览器
            response.getWriter().write(JSONObject.fromObject(result).toString());
            return;
        }

        // 通过id，在数据库中查询该商品
        Content content = contentService.findById(id);
        // 商品不存在
        if (content == null) {
            // 设置异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "购买失败", JSONResult.ERROR_RESULT);
            return;
        }

        // 封装订单数据
        TransactionRecord record = new TransactionRecord();
        record.setContent(content);
        record.setPerson(loginPerson);
        record.setPrice(content.getPrice().intValue());
        record.setTime(new Date().getTime());

        // 保存订单到数据库
        Integer res = transactionRecordService.save(record);

        if (res == 0) {
            // 设置异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "购买失败", JSONResult.ERROR_RESULT);
        } else {
            // 购买成功
            JSONResult.setStatusCode(result, JSONResult.SUCCESS_CODE, JSONResult.SUCCESS_MESSAGE, JSONResult.SUCCESS_RESULT);
        }

        // 将响应结果对象转换为字符串并响应给浏览器
        response.getWriter().write(JSONObject.fromObject(result).toString());
    }
}
