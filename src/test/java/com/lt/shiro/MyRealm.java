package com.lt.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @description: 自定义登录认证器
 * @author: ~Teng~
 * @date: 2022/9/28 12:33
 */
//public class MyRealm extends AuthenticatingRealm {
//    /**
//     * 自定义的登录认证方法， Shiro 的 subject.login 方法底层会调用该类的认证方法完成登录认证
//     * 需要配置自定义的 realm 生效，在 ini 文件中配置，或 Springboot 中配置
//     * 该方法只是获取进行对比的信息，认证逻辑还是按照 Shiro 的底层认证逻辑完成认证
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        // 1. 通过 token 获取身份信息
//        String username = authenticationToken.getPrincipal().toString();
//        // 2. 获取认证信息
//        String password = new String((char[]) authenticationToken.getCredentials());
//        System.out.println("认证用户的信息：" + username + "----" + password);
//        // 3. 获取数据库中存储的用户信息
//        if ("zhangsan".equals(username)) {
//            // 3.1 数据库中存储的加盐3次迭代的密码
//            String pwdInfo = "7174f64b13022acd3c56e2781e098a5f";
//            // 3.2 创建封装校验逻辑对象，封装数据然后返回
//            AuthenticationInfo info = new SimpleAuthenticationInfo(
//                    authenticationToken.getPrincipal(),
//                    pwdInfo,
//                    ByteSource.Util.bytes("salt"),
//                    username
//            );
//            return info;
//        }
//        return null;
//    }
//}
