package com.codeSpec.blog.vo.params;

import lombok.Data;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 20:05
 * @Description:
 */
@Data
public class LoginParam {

    private String account;

    private String password;

    private String nickname;
}
