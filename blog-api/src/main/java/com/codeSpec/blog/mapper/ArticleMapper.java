package com.codeSpec.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codeSpec.blog.table.Article;
import com.codeSpec.blog.vo.result.Archives;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: pinga-a
 * @Date: 2021-09-02 19:38
 * @Description: 文章首页mapper
 */
public interface ArticleMapper extends BaseMapper<Article> {


    @Select({"<script>",
            "select * from ms_article where 1 = 1 ",
            "<if test='categoryId != null'>", "and categoryId = #{categoryId} ", "</if>",
            "<if test='tagId != null'>", "and id in (select article_id from ms_article_tag where tag_id = #{tagId})", "</if>",
            "<if test='year != null and year.length > 0 and month != null and month.length > 0'>",
            "and UNIX_TIMESTAMP(create_date/1000,'%Y') = #{year} and  UNIX_TIMESTAMP(create_date/1000,'%m') = #{month}", "</if>",
            "</script>"})
    IPage<Article> listArticle(Page<Article> page,
                               Long categoryId,
                               Long tagId,
                               String year,
                               String month);

    @Select("select  m.id, m.view_counts ,m.title from ms_article m order by view_counts desc limit 5")
    List<Article> listHotArticle();

    @Select("select  m.id, m.create_date ,m.title from ms_article m order by create_date desc limit 5")
    List<Article> listNewArticle();


    @Select("select FROM_UNIXTIME(create_date/1000,'%Y') as year,FROM_UNIXTIME(create_date/1000,'%m') as month,count(*) as count from ms_article group by year,month")
    List<Archives> listArchives();
}
