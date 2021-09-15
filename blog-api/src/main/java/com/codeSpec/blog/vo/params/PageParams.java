package com.codeSpec.blog.vo.params;

import lombok.Data;

/**
 * @Author: pinga-a
 * @Date: 2021-09-02 18:02
 * @Description: 前端请求参数封装vo
 */
@Data
public class PageParams {

    private int page = 1;

    private int pageSize = 10;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 标签id
     */
    private Long tagId;

    private String year;

    private String month;

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0"+this.month;
        }
        return this.month;
    }
}
