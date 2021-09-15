package com.codeSpec.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeSpec.blog.table.SysUser;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: pinga-a
 * @Date: 2021-09-06 20:29
 * @Description: 用户实现
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from ms_sys_user m where account = #{account}")
    SysUser findUserByCount(String account);

}
