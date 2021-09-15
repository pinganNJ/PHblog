package com.codeSpec.blog.controller;

import com.codeSpec.blog.result.Result;
import com.codeSpec.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pinga-a
 * @Date: 2021-09-03 10:07
 * @Description:
 */

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/hot")
    public Result listTagHot() {
        int limit = 6;
        return tagService.listTagHot(limit);

    }


}
