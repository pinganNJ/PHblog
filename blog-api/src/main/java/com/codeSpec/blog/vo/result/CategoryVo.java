package com.codeSpec.blog.vo.result;

import lombok.Data;

/**
 * @Author: pinga-a
 * @Date: 2021-09-14 21:53
 * @Description: 文章分类
 */

@Data
public class CategoryVo {

    private String id;

    private String avatar;

    private String categoryName;

    private String description;
}
