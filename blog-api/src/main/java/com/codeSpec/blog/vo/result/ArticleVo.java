package com.codeSpec.blog.vo.result;

import lombok.Data;

import java.util.List;

/**
 * @Author: pinga-a
 * @Date: 2021-09-14 21:50
 * @Description: 文章详情页vo
 */
@Data
public class ArticleVo {
    private String id;

    /**
     * 标题
     */
    private String title;
    /**
     * 梗概
     */
    private String summary;
    /**
     * 评论数量
     */
    private Integer commentCounts;
    /**
     * 阅读数量
     */
    private Integer viewCounts;
    /**
     * 是否置顶
     */
    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 作者
     */
    private String author;
    /**
     * 文章内容
     */
    private ArticleBodyVo body;
    /**
     * 文章标签集合
     */
    private List<TagVo> tags;
    /**
     * 文章分类集合
     */
    private CategoryVo category;

}
