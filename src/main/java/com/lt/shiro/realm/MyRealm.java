package com.lt.shiro.realm;


import com.lt.shiro.entity.User;
import com.lt.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/9/28 15:49
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 自定义授权方法：获取当前登录用户权限信息，返回给 Shiro 用来进行授权对比
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入自定义授权方法");
        // 1. 获取当前用户身份信息
        String principal = principalCollection.getPrimaryPrincipal().toString();
        List<String> roles = userService.getUserRoleInfo(principal);
        System.out.println("当前用户角色信息：" + roles);
        // 2. 获取该身份对应权限信息
        List<String> permissions = userService.getUserPermissionInfo(roles);
        System.out.println("当前用户权限信息：" + permissions);
        // 2. 创建对象，存储当前登录的用户的权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        // 3. 返回
        return info;
    }

    /**
     * 自定义登录认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 获取用户名
        String username = authenticationToken.getPrincipal().toString();
        // 2. 根据用户名查询用户
        User user = userService.getUserInfoByName(username);
        // 3. 判断并将数据完成封装
        if (user != null) {
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), user.getPwd(), ByteSource.Util.bytes("salt"), username);
        }
        return null;
    }
}
