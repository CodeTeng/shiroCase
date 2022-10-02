package com.lt.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.lt.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/9/28 15:57
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    /**
     * 配置 SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // 1. 创建 defaultWebSecurityManager 对象
        DefaultWebSecurityManager defaultWebSecurityManager = new
                DefaultWebSecurityManager();
//        // 多个 Realm 认证策略配置
//        // 2. 创建认证对象，并设置认证策略
//        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
//        // 所有 Realm 成功 才算认证成功
//        modularRealmAuthenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
//        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator);
//        // 3. 封装 Realm 集合
//        List<Realm> list = new ArrayList<>();
//        list.add(myRealm);
//        defaultWebSecurityManager.setRealms(list);
        // 2. 创建加密对象，并设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 2.1 采用 md5 加密
        matcher.setHashAlgorithmName("MD5");
        // 2.2 迭代加密次数
        matcher.setHashIterations(3);
        // 3 将加密对象存储到 myRealm 中
        myRealm.setCredentialsMatcher(matcher);
        // 4 将 myRealm 存入 defaultWebSecurityManager 对象
        defaultWebSecurityManager.setRealm(myRealm);
        // 设置 rememberMe 功能
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager());
        // 5 返回
        return defaultWebSecurityManager;
    }

    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 设置跨域
        // simpleCookie.setDomain("domain");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
        return simpleCookie;
    }

    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("806823".getBytes());
        return cookieRememberMeManager;
    }

    /**
     * 配置 Shiro 内置过滤器拦截范围
     */
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition
                = new DefaultShiroFilterChainDefinition();
        // 设置不认证可以访问的资源
        chainDefinition.addPathDefinition("/myController/userLogin", "anon");
        chainDefinition.addPathDefinition("/myController/login", "anon");
        // 配置登出过滤器
        chainDefinition.addPathDefinition("/logout", "logout");
        // 设置需要进行登录认证的拦截范围
        chainDefinition.addPathDefinition("/**", "authc");
        // 添加存在用户的过滤器（ rememberMe ）
        chainDefinition.addPathDefinition("/**", "user");
        return chainDefinition;
    }

    /**
     * 用于解析 thymeleaf 中的 shiro:相关属性
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
