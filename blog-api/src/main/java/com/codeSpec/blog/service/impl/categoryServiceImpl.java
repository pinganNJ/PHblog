package com.codeSpec.blog.service.impl;

import com.codeSpec.blog.mapper.CategoryMapper;
import com.codeSpec.blog.service.CategoryService;
import com.codeSpec.blog.table.Category;
import com.codeSpec.blog.vo.result.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: pinga-a
 * @Date: 2021-09-15 11:23
 * @Description:
 */

@Service
public class categoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo getCateById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        return copy(category);
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }
}
