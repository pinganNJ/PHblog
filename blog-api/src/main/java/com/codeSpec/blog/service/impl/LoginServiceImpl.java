package com.codeSpec.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codeSpec.blog.enums.ErrorCode;
import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.LoginService;
import com.codeSpec.blog.service.SysUserService;
import com.codeSpec.blog.table.SysUser;
import com.codeSpec.blog.utils.JWTUtils;
import com.codeSpec.blog.utils.RedisKeys;
import com.codeSpec.blog.utils.RedisUtils;
import com.codeSpec.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 20:08
 * @Description: 登录实现类
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private static String salt = "pizan!@#$%^&*";

    @Autowired
    private SysUserService sysUserService;

    @Override

    public Result login(LoginParam loginParam) {
        /**
         * 1. 检查参数是否合法（判空）
         * 2. 根据用户名和密码去user表中查询 是否存在
         * 3. 如果不存在 登录失败
         * 4. 如果存在 ，使用jwt 生成token 返回给前端
         * 5. token放入redis当中，redis  token：user信息 设置过期时间
         *  (登录认证的时候 先认证token字符串是否合法，去redis认证是否存在)
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        //数据库中保存的是md5加密后的密码，查询的时候也需要先进行加密
        password = DigestUtils.md5Hex(password + salt);
        SysUser user = sysUserService.findUser(account, password);

        //如果数据库里面不存在此用户，则登陆失败
        if (null == user) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(user.getId());
        //存入redis，以秒为单位,缓存时间为一天
        RedisUtils.set(RedisKeys.tokenKey(token), JSONObject.toJSONString(user), 24 * 60 * 60);
        return Result.success(token);
    }

    /**
     * 根据token查找当前用户
     *
     * @param token
     * @return
     */
    @Override
    public SysUser checkToken(String token) {
        //判空
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        //调用JWTUtils检查token
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (null == stringObjectMap) {
            return null;
        }

        //查看redis里面有没有缓存
        String userStr = RedisUtils.get(RedisKeys.tokenKey(token));
        if (StringUtils.isEmpty(userStr)) {
            return null;
        }
        //将json转换为user
        return JSONObject.parseObject(userStr, SysUser.class);
    }

    /**
     * 登出服务
     *
     * @param token
     * @return
     */
    @Override
    public Result logout(String token) {

        //直接将redis里面的缓存删除
        RedisUtils.del(RedisKeys.tokenKey(token));
        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1、判空
         * 2. 从数据库获取user，如果存在就返回对象已经存在,若不存在，往数据库里面插入数据
         * 2、根据userId生成返回token
         */
        String account = loginParam.getAccount();
        String passWord = loginParam.getPassword();
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(passWord)) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //如果数据库里面存在此用户，则说明用户已经存在，注册失败
        SysUser user = sysUserService.findUserByCount(account);
        if (null != user) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        user.setAccount(account);
        user.setNickname(loginParam.getNickname());
        //密码保存的时候需要md5加密
        user.setPassword(DigestUtils.md5Hex(passWord + salt));
        user.setSalt(salt);
        //此用户是否被删除
        user.setDeleted(0);
        //邮箱，暂时用不到
        user.setEmail("");
        //头像，此处未做处理
        user.setAvatar("/static/img/logo.b3a48c0.png");
        //手机号码，暂时不处理
        user.setMobilePhoneNumber("");
        //创建和最后登录时间，毫秒计算
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setStatus("");

        sysUserService.save(user);

        //生成token并存入redis
        String token = JWTUtils.createToken(user.getId());
        RedisUtils.set(RedisKeys.tokenKey(token), token, 60 * 60 * 24);

        return Result.success(token);
    }
}
