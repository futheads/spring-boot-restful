package com.futhead.restful.dao;

import com.futhead.restful.model.po.SysUser;
import com.futhead.restful.model.vo.SysUserQueryVo;
import com.futhead.restful.util.StringUtils;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by futhead on 2017-8-9.
 */
@Mapper
public interface SysUserMapper {

    @UpdateProvider(type = SysUserSqlBuilder.class, method = "buildSysUserUpdateSelective")
    void updateByPrimaryKeySelective(SysUser sysUser);

    @SelectProvider(type = SysUserSqlBuilder.class, method = "buildSysUserQuery")
    Page<SysUserQueryVo> queryByConditions(SysUserQueryVo queryVo);


    class SysUserSqlBuilder {

        public String buildSysUserUpdateSelective(final SysUser sysUser) {
            return new SQL() {{
                UPDATE("sys_user");
                if (sysUser.getUsername() != null) {
                    SET("username = #{username,jdbcType=VARCHAR}");
                }
                if (sysUser.getPassword() != null) {
                    SET("password = #{password,jdbcType=VARCHAR}");
                }
                if (sysUser.getPickname() != null) {
                    SET("pickname = #{pickname,jdbcType=VARCHAR}");
                }
                if (sysUser.getAge() != 0) {
                    SET("age = " + sysUser.getAge());
                }
                WHERE("id = " + sysUser.getId());
            }}.toString();

        }

        public String buildSysUserQuery(final SysUserQueryVo queryVo) {
            return new SQL() {{
                SELECT("su.username, su.pickname, su.age, sr.name as role");
                FROM("sys_user su");
                LEFT_OUTER_JOIN("sys_role_user sru ON su.id = sru.sys_user_id");
                LEFT_OUTER_JOIN("sys_role sr ON sr.id = sru.sys_role_id");
                if (!StringUtils.isEmpty(queryVo.getUsername())) {
                    WHERE("su.username like '%" + queryVo.getUsername() + "%'");
                }
                if (!StringUtils.isEmpty(queryVo.getPickname())) {
                    WHERE("su.pickname like '%" + queryVo.getPickname() + "%'");
                }
                if (!StringUtils.isEmpty(queryVo.getRole())) {
                    WHERE(" and sr.name like '%" + queryVo.getRole() + "%'");
                }
                if (queryVo.getAge() > 0) {
                    WHERE("age = #{age}");
                }
                ORDER_BY("su.id DESC");
            }}.toString();
        }
    }
}
