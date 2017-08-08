package com.futhead.restful.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.futhead.restful.model.po.SysRole;
import com.futhead.restful.model.po.SysUser;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
public class SysUserWithRoleVo extends SysUser {

    public SysUserWithRoleVo(SysUser sysUser) {
        this.setId(sysUser.getId());
        this.setUsername(sysUser.getUsername());
        this.setPassword(sysUser.getPassword());
        this.setPickname(sysUser.getPickname());
        this.setAge(sysUser.getAge());
    }

    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysUserWithRoleVo{" +
                "roles=" + roles +
                '}';
    }
}
