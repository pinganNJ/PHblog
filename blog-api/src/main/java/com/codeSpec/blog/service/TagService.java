package com.codeSpec.blog.service;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.vo.result.TagVo;

import java.util.List;

/**
 * @Author: pinga-a
 * @Date: 2021-09-03 10:14
 * @Description: 标签service
 */
public interface TagService {

    Result listTagHot(int limit);

    List<TagVo> getTagsByArticleId(Long id);
}
