package com.codeSpec.blog.service;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.table.SysUser;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 20:24
 * @Description: 用户相关功能
 */
public interface SysUserService {
    /**
     * 查找数据库中的user
     * @param account
     * @return
     */
    SysUser findUserByCount(String account);

    /**
     * 根据token,获取当前登录的用户
     * @param token
     * @return
     */
    Result currentUser(String token);

    /**
     * 保存新用户
     * @param user
     */
    void save(SysUser user);

    /**
     * 登录的时候根据account和password查找用户
     * @param account
     * @param password
     * @return
     */
    SysUser findUser(String account, String password);
}
