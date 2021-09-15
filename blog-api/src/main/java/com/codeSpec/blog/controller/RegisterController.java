package com.codeSpec.blog.controller;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.LoginService;
import com.codeSpec.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pinga-a
 * @Date: 2021-09-09 22:36
 * @Description: 注册
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @PostMapping("register")
    public Result register(LoginParam loginParam) {
        return loginService.register(loginParam);
    }



}
