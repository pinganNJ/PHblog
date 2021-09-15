package com.codeSpec.blog.service.impl;

import com.codeSpec.blog.mapper.TagMapper;
import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.TagService;
import com.codeSpec.blog.table.Tag;
import com.codeSpec.blog.vo.result.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: pinga-a
 * @Date: 2021-09-03 10:15
 * @Description: 标签业务实现类
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Result listTagHot(int limit) {
        //todo:此处返回的是整个Tag，可以创建一个tagVo,查询的时候也只查某几个字段
        List<Tag> tags = tagMapper.listTagHot(limit);
        return Result.success(tags);
    }

    @Override
    public List<TagVo> getTagsByArticleId(Long id) {
        //mybatisplus 无法进行多表查询
        List<Tag> tags = tagMapper.getTagsByArticleId(id);
        return copyList(tags);
    }


    /**
     * 将数据库查询出来的tag集合转换成返回给前端的vo集合（这里的vo和tag的属性是一样的）
     * @param tags
     * @return
     */
    private List<TagVo> copyList(List<Tag> tags) {
        ArrayList<TagVo> tagVos = new ArrayList<>();
        for (Tag tag : tags) {
            tagVos.add(copy(tag));
        }
        return tagVos;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
}
