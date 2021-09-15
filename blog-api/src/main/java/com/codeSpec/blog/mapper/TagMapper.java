package com.codeSpec.blog.mapper;

import com.codeSpec.blog.table.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: pinga-a
 * @Date: 2021-09-03 10:16
 * @Description:
 */
public interface TagMapper {
    @Select("select distinct t.id , t.tag_name from ms_tag t left join ms_article_tag at  on t.id = at.tag_id where at.article_id in (select m.id from  " +
            "(select  * from ms_article order by view_counts desc limit  5) as m )")
    List<Tag> listTagHot(int limit);

    @Select("select t.id ,t.avatar, t.tag_name as tagName from  ms_article_tag at left join ms_tag t on at.tag_id = t.id where at.article_id = '1'")
    List<Tag> getTagsByArticleId(Long id);
}
