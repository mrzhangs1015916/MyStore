package com.netease.mystore.web.controller;

import com.netease.mystore.domain.Person;
import com.netease.mystore.service.PersonService;
import com.netease.mystore.web.common.JSONResult;
import com.netease.mystore.web.common.Product;
import com.netease.mystore.web.common.User;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户控制器
 * Created by switch on 16/11/14.
 */
@Controller
public class UserController {
    /**
     * 注入用户服务bean
     */
    @Resource
    private PersonService personService;


    /**
     * 显示登陆界面
     *
     * @return
     */
    @RequestMapping("/login")
    public String showLoginUI() {
        return "login";
    }

    /**
     * 登陆,异步接口
     */
    @RequestMapping("/api/login")
    public void login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 测试接口是否有效
        // System.out.println(userName + " " + password);

        // 创建用户并设置属性
        Person person = new Person();
        person.setUserName(userName);
        person.setPassword(password);

        // 查询该用户是否存在,不存在则为null
        person = personService.login(person);

        // 创建JSON响应结果对象
        JSONResult result = new JSONResult();

        // 用户不存在
        if (person == null) {
            // 异常状态码
            JSONResult.setStatusCode(result, JSONResult.ERROR_CODE, "登录失败", JSONResult.ERROR_RESULT);
        } else {
            // 登录成功
            JSONResult.setStatusCode(result, JSONResult.SUCCESS_CODE, JSONResult.SUCCESS_MESSAGE, JSONResult.SUCCESS_RESULT);

            // 创建用户类型对象
            User user = new User();
            user.setUsername(person.getUserName());
            user.setUsertype(person.getUserType());

            // 设置属性到session域中
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("loginPerson", person);
        }

        // 将响应结果对象转换为字符串并响应给浏览器
        response.getWriter().write(JSONObject.fromObject(result).toString());
    }

    /**
     * 注销，然后跳转到登录界面
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("loginPerson");
        return "login";
    }


    /**
     * 显示账务页面
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/account")
    public String showAccountUI(HttpServletRequest request, ModelMap map) {
        // 获取登陆用户
        Person loginPerson = (Person) request.getSession().getAttribute("loginPerson");
        // 没有登陆，跳转到登陆界面
        if (loginPerson == null) {
            return "redirect:/login";
        }

        // 获取该用户已购买的产品列表
        List<Product> buyList = personService.getBuyProductList();

        map.addAttribute("buyList", buyList);
        return "account";
    }
}
