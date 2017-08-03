package com.futhead.restful.service;

import com.futhead.restful.dao.SysRoleMapper;
import com.futhead.restful.dao.SysUserRepository;
import com.futhead.restful.model.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/1.
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    public SysUser findSysUserByUsername(String username) {
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if (sysUser != null) {
            sysUser.setRoles(sysRoleMapper.findRolesByUserId(sysUser.getId()));
        }
        return sysUser;
    }
}
