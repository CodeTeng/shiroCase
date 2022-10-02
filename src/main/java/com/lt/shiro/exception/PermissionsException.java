package com.lt.shiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;

/**
 * @description: 认证异常处理类
 * @author: ~Teng~
 * @date: 2022/10/2 15:19
 */
@RestControllerAdvice
public class PermissionsException {

    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(Exception ex) {
        return "无权限";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String authorizationException(Exception ex) {
        return "权限验证失败";
    }
}
