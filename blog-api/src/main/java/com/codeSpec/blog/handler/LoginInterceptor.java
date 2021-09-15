package com.codeSpec.blog.handler;

import com.alibaba.fastjson.JSONObject;
import com.codeSpec.blog.enums.ErrorCode;
import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.LoginService;
import com.codeSpec.blog.table.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

/**
 * @Author: pinga-a
 * @Date: 2021-09-14 20:58
 * @Description: 登录拦截器
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //todo：这是什么意思？
        if (!(handler instanceof MethodHandle)) {
            return true;
        }

        //是否有token
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSONObject.toJSONString(result));
            return false;
        }

        //通过token检车是否有当前用户
        SysUser user = loginService.checkToken(token);
        if (null == user) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSONObject.toJSONString(result));
            return false;
        }

        //是登录状态，放行
        return true;
    }
}
