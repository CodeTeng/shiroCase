package com.lt.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/9/28 11:40
 */
//public class ShiroTests {
//
//    /**
//     * 身份认证
//     */
//    @Test
//    void authenticationTest() {
//        // 1. 获取 SecurityManager
//        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        // 2. 获取 Subject 对象
//        Subject subject = SecurityUtils.getSubject();
//        // 3. 创建 token 对象， web 应用用户名密码从页面传递
//        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "z3");
//        try {
//            // 4. 完成登录
//            subject.login(token);
//            System.out.println("登录成功");
//            // 5. 判断角色
//            boolean result = subject.hasRole("role1");
//            System.out.println("是否拥有此角色：" + result);
//            // 6. 判断权限
//            boolean isPermitted = subject.isPermitted("user:insert");
//            System.out.println("是否拥有此权限：" + isPermitted);
//            // 也可以用 checkPermission 方法，但没有返回值，没权限抛 AuthenticationException
//            // subject.checkPermission("user:select");
//        } catch (UnknownAccountException e) {
//            e.printStackTrace();
//            System.out.println("用户不存在");
//        } catch (IncorrectCredentialsException e) {
//            e.printStackTrace();
//            System.out.println("密码错误");
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            System.out.println("登录失败");
//        }
//    }
//
//    /**
//     * Shiro 加密
//     */
//    @Test
//    void shiroMD5Test() {
//        // 密码明文
//        String password = "z3";
//        // 使用 MD5 加密
//        Md5Hash md5Hash = new Md5Hash(password);
//        System.out.println("MD5 加密：" + md5Hash.toHex());
//        // 带盐的 md5 加密，盐就是在密码明文后拼接新字符串，然后再进行加密
//        Md5Hash md5Hash2 = new Md5Hash(password, "salt");
//        System.out.println("MD5 带盐加密：" + md5Hash2.toHex());
//        // 为了保证安全，避免被破解还可以多次迭代加密，保证数据安全
//        Md5Hash md5Hash3 = new Md5Hash(password, "salt", 3);
//        System.out.println("MD5 带盐加密3次：" + md5Hash3.toHex());
//        // 使用父类实现加密
//        SimpleHash simpleHash = new SimpleHash("MD5", password, "salt", 3);
//        System.out.println("父类带盐三次加密：" + simpleHash.toHex());   // 和上者一样
//    }
//}
