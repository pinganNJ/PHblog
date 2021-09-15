package com.codeSpec.blog.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: pinga-a
 * @Date: 2021-09-02 15:22
 * @Description: 结果封装
 */

@Data
@AllArgsConstructor
public class Result {

    private  boolean success;

    private Integer code;

    private String msg;

    private Object data;

    /**
     * 成功
     */
    public static Result success(Object data) {
        return new Result(true, 200,"success", data);
    }

    /**
     * 失败
     */
    public static Result fail(Integer code, String msg) {
        return new Result(false, code, msg, null);
    }

}
