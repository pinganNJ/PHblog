package com.codeSpec.blog.utils;

/**
 * @Author: pinga-a
 * @Date: 2021-09-09 21:50
 * @Description: redis键统一管理类
 */
public class RedisKeys {


    /**
     * token令牌key
     * @param token
     * @return
     */
    public static String tokenKey(String token) {
        return  "token_" + token;
    }

}
