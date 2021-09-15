package com.codeSpec.blog.service;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.table.SysUser;
import com.codeSpec.blog.vo.params.LoginParam;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 20:06
 * @Description: 登录功能
 */
public interface LoginService {

    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParam loginParam);
}
