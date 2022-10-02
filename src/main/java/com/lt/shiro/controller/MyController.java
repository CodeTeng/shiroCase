package com.lt.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/9/28 16:04
 */
@Controller
@RequestMapping("/myController")
public class MyController {

    /**
     * 跳转登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录认证
     */
    @GetMapping("/userLogin")
    public String userLogin(String name, String pwd,
                            @RequestParam(defaultValue = "false") boolean rememberMe,
                            HttpSession session) {
        // 1. 获取 subject 对象
        Subject subject = SecurityUtils.getSubject();
        // 2. 封装请求数据到 token 对象中
        AuthenticationToken token = new UsernamePasswordToken(name, pwd, rememberMe);
        try {
            // 3. 调用 login 方法 进行验证
            subject.login(token);
            session.setAttribute("user", token.getPrincipal().toString());
            return "main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }

    /**
     * 登录认证验证 rememberMe
     */
    @GetMapping("/userLoginRm")
    public String userLogin(HttpSession session) {
        session.setAttribute("user", "rememberMe");
        return "main";
    }

    /**
     * 登录认证验证角色
     */
    @GetMapping("/userLoginRoles")
    @RequiresRoles("admin")
    @ResponseBody
    public String userLoginRoles() {
        System.out.println("登录认证验证角色");
        return "验证角色成功";
    }

    /**
     * 登录认证验证权限
     */
    @GetMapping("/userPermissions")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public String userLoginPermissions() {
        System.out.println("登录认证验证权限");
        return "验证权限成功";
    }
}
