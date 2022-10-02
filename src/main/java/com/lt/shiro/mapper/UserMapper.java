package com.lt.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.shiro.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2022/9/28 15:47
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询对应角色信息
     *
     * @param principal 用户身份---用户名
     * @return 用户角色信息
     */
    List<String> getUserRoleInfo(@Param("principal") String principal);

    /**
     * 根据角色名查询对应权限信息
     *
     * @param roles 角色名集合
     * @return 该角色对应的权限集合
     */
    List<String> getUserPermissionInfo(@Param("roles") List<String> roles);
}