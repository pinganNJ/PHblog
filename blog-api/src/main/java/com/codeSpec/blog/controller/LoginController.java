package com.codeSpec.blog.controller;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.LoginService;
import com.codeSpec.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 20:03
 * @Description:
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    public Result login(@RequestBody LoginParam loginParam){

        return loginService.login(loginParam);

    }

}
