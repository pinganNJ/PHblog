package com.codeSpec.blog.controller;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.ArticleService;
import com.codeSpec.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: pinga-a
 * @Date: 2021-09-02 17:09
 * @Description: 文章相关方法的controller
 */
@RestController
@RequestMapping("articles")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    /**
     * 多条件文章首页列表（如文章的标签，分类，年月等）
     *
     * @param pageParams
     * @return
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams) {

        return articleService.listArticle(pageParams);
    }

    /**
     * 首页-最热文章
     *
     * @param
     * @return
     */
    @PostMapping("/hot")
    public Result hotArticle() {
        return articleService.listHotArticle();

    }


    /**
     * 首页-最新文章
     *
     * @param
     * @return
     */
    @PostMapping("/new")
    public Result newArticle() {
        return articleService.listNewArticle();
    }


    /**
     * 文章归档
     *
     * @param
     * @return
     */
    @PostMapping("/listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }


    /**
     * 文章详情
     * @param id
     * @return
     */
    @PostMapping("/view/{id}")
    public Result articleDetail(@PathVariable("id") Long id) {
        return articleService.articleDetail(id);
    }

}
