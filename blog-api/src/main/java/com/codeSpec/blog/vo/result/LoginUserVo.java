package com.codeSpec.blog.vo.result;

import lombok.Data;

/**
 * @Author: pinga-a
 * @Date: 2021-09-09 22:20
 * @Description: 获取当前登录用户
 */

@Data
public class LoginUserVo {

    private String id;

    private String account;

    private String nickname;

    private String avatar;
}
