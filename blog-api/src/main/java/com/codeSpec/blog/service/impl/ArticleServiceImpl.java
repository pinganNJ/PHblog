package com.codeSpec.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codeSpec.blog.mapper.ArticleBodyMapper;
import com.codeSpec.blog.mapper.ArticleMapper;
import com.codeSpec.blog.mapper.SysUserMapper;
import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.ArticleService;
import com.codeSpec.blog.service.CategoryService;
import com.codeSpec.blog.service.TagService;
import com.codeSpec.blog.table.Article;
import com.codeSpec.blog.table.ArticleBody;
import com.codeSpec.blog.vo.params.PageParams;
import com.codeSpec.blog.vo.result.Archives;
import com.codeSpec.blog.vo.result.ArticleBodyVo;
import com.codeSpec.blog.vo.result.ArticleVo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: pinga-a
 * @Date: 2021-09-02 19:32
 * @Description:
 */

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;



    @Override
    public Result listArticle(PageParams pageParams) {
        //多条件获取首页文章
        Page<Article> page = new Page(pageParams.getPage(), pageParams.getPageSize());
        IPage<Article> articleIPage = articleMapper.listArticle(
                page,
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth());
        List<Article> records = articleIPage.getRecords();
        return Result.success(copyList(records));

    }

    private List<ArticleVo> copyList(List<Article> records) {
        ArrayList<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : records) {
            articleVos.add(copy(article, true, true,false,false));
        }
        return articleVos;
    }

    @Override
    public Result listHotArticle() {

        List<Article> articles = articleMapper.listHotArticle();
        return Result.success(articles);
    }

    @Override
    public Result listNewArticle() {

        List<Article> articles = articleMapper.listNewArticle();
        return Result.success(articles);
    }

    @Override
    public Result listArchives() {
        List<Archives> articles = articleMapper.listArchives();

        return Result.success(articles);
    }

    @Override
    public Result articleDetail(Long id) {
        /**
         * 1、获取文章
         * 2、填充细节
         */
        Article article = articleMapper.selectById(id);
        //将主体的article填充，返回给前端vo（不然需要在sql语句中应用join,并且还要在sql里面用if判断一下）
        ArticleVo articleVo = copy(article, true, true, true, true);
        return Result.success(articleVo);
    }

    public ArticleVo copy(Article article, boolean isTag, boolean isCategory, boolean isAuthor, boolean isBody) {
        ArticleVo articleVo = new ArticleVo();
        //todo：这个copyProperties不能拷贝id么，为啥还要单独将id作赋值？因为vo中的id是string，初始的id是long
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setId(article.getId().toString());
        log.info("arvoId:" + articleVo + "-----------" + "article" + article);

        //转化一下日期
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));

        if (isTag) {
            //通过articleId查询并返回tag集合
            articleVo.setTags(tagService.getTagsByArticleId(article.getId()));
        }
        if (isCategory) {
            //通过articleId查询并返回Category（目前是一对一的关系，要是一个文章有多个分类，就需要和tag一样，单独弄一个表）
            articleVo.setCategory(categoryService.getCateById(article.getCategoryId()));
        }

        if (isAuthor) {
            //通过articleId查询并返回Author
            articleVo.setAuthor(sysUserMapper.selectById(article.getAuthorId()).getNickname());
        }
        if (isBody) {
            //通过articleId查询并返回Body
            articleVo.setBody(getArticleBodyById(article.getBodyId()));
        }
        return articleVo;
    }

    private ArticleBodyVo getArticleBodyById(Long bodyId) {
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        //todo 为了一个content也需要创建一个vo么？
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }


}
