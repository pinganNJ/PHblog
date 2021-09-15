package com.codeSpec.blog.service;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.vo.params.PageParams;

/**
 * @Author: pinga-a
 * @Date: 2021-09-02 19:30
 * @Description: 文章接口
 */
public interface ArticleService  {

    /**
     * 多条件获取首页文章
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    Result listHotArticle();

    Result listNewArticle();

    Result listArchives();

    Result articleDetail(Long id);
}
