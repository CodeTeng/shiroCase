package com.lt.shiro.service;

import com.lt.shiro.entity.User;

import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/9/28 15:47
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param name 用户名
     * @return 用户
     */
    User getUserInfoByName(String name);

    /**
     * 根据用户名查询对应角色信息
     *
     * @param principal 用户身份---用户名
     * @return 用户角色信息
     */
    List<String> getUserRoleInfo(String principal);

    /**
     * 根据角色名查询对应权限信息
     *
     * @param roles 角色名集合
     * @return 该角色对应的权限集合
     */
    List<String> getUserPermissionInfo(List<String> roles);
}
