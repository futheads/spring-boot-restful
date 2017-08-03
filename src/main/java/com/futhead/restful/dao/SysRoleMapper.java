package com.futhead.restful.dao;

import com.futhead.restful.model.po.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Mapper
public interface SysRoleMapper {

    @Select("SELECT SR.* FROM SYS_ROLE_USER SRU LEFT JOIN SYS_ROLE SR ON SRU.SYS_ROLE_ID = sr.id WHERE sru.sys_user_id = #{id}")
    List<SysRole> findRolesByUserId(int id);

}
