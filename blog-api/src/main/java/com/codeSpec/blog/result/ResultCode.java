package com.codeSpec.blog.result;

/**
 * @Author: pinga-a
 * @Date: 2021-09-02 15:22
 * @Description: 结果编码封装
 */
public enum  ResultCode {

    SUCCESS("处理成功", 200);

    private String desc;
    private Integer code;

    ResultCode(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

}
