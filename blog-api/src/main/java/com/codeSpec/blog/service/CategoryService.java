package com.codeSpec.blog.service;

import com.codeSpec.blog.vo.result.CategoryVo;

/**
 * @Author: pinga-a
 * @Date: 2021-09-15 11:20
 * @Description: 文章分类service
 */
public interface CategoryService {

    /**
     * 通过文章id查询分类
     * @return
     */
    CategoryVo getCateById(Long categoryId);
}
