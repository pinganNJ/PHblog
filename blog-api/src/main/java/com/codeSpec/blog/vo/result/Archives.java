package com.codeSpec.blog.vo.result;

import lombok.Data;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 19:36
 * @Description: 文章归档，返回参数
 */

@Data
public class Archives {
    private Integer year;

    private Integer month;

    private Long count;
}
