package com.lt.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/10/2 15:38
 */
@SpringBootTest
public class SessionTests {

    @Test
    void test1() {
        // 1. 实现
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("key", "value");
        // 2. 说明
        /**
         * Controller 中的 request，在 shiro 过滤器中的 doFilerInternal 方法，被包装成
         * ShiroHttpServletRequest。
         * SecurityManager 和 SessionManager 会话管理器决定 session 来源于 ServletRequest
         * 还是由 Shiro 管理的会话。
         * 无论是通过 request.getSession 或 subject.getSession 获取到 session，操作
         * session，两者都是等价的。
         */
    }
}
