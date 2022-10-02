package com.lt.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lt.shiro.entity.User;
import com.lt.shiro.mapper.UserMapper;
import com.lt.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/9/28 15:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> getUserRoleInfo(String principal) {
        return userMapper.getUserRoleInfo(principal);
    }

    @Override
    public List<String> getUserPermissionInfo(List<String> roles) {
        return userMapper.getUserPermissionInfo(roles);
    }
}
