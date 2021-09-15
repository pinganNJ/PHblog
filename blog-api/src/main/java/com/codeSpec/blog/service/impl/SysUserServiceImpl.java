package com.codeSpec.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeSpec.blog.mapper.SysUserMapper;
import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.LoginService;
import com.codeSpec.blog.service.SysUserService;
import com.codeSpec.blog.table.SysUser;
import com.codeSpec.blog.vo.result.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 20:26
 * @Description: 用户类
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private LoginService loginService;

    @Override
    public SysUser findUserByCount(String account) {

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SysUser::getAccount, account);
        //下面有selectOne这里应该就不用写limit了吧
        wrapper.last("limit 1");
        return sysUserMapper.selectOne(wrapper);

        //直接写sql语句
        //return sysUserMapper.findUserByCount(account);
    }

    @Override
    public Result currentUser(String token) {
        /**
         * 1. 校验当前的token是否可用（判空）
         * 2. 根据token从redis里面查找到用户信息
         * 3. 如果不存在 登录失败
         * 4. 如果存在 ，使用jwt 生成token 返回给前端
         * 5. token放入redis当中，redis  token：user信息 设置过期时间
         *  (登录认证的时候 先认证token字符串是否合法，去redis认证是否存在)
         */
        SysUser user = loginService.checkToken(token);

        //组装返回结果
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(String.valueOf(user.getId()));
        loginUserVo.setAccount(user.getAccount());
        loginUserVo.setNickname(user.getNickname());
        loginUserVo.setAvatar(user.getAvatar());

        return Result.success(loginUserVo);
    }

    @Override
    public void save(SysUser user) {
        sysUserMapper.insert(user);
    }

    @Override
    public SysUser findUser(String account, String password) {
        //感觉这样写比较难受，没有直接写sql看起来清爽
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getAccount, account).
                eq(SysUser::getPassword, password).
                select(SysUser::getId, SysUser::getAvatar, SysUser::getAccount, SysUser::getNickname);
        wrapper.last("limit 1");
        return sysUserMapper.selectOne(wrapper);
    }
}
