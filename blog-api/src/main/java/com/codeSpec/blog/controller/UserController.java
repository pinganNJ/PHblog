package com.codeSpec.blog.controller;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pinga-a
 * @Date: 2021-09-07 20:10
 * @Description: 用户控制器
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {

        /**
         * 根据token,获取当前登录的用户
         */
        return sysUserService.currentUser(token);
    }
}
