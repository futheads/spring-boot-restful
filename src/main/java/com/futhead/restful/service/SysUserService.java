package com.futhead.restful.service;

import com.futhead.restful.dao.SysRoleMapper;
import com.futhead.restful.dao.SysUserMapper;
import com.futhead.restful.dao.SysUserRepository;
import com.futhead.restful.model.po.SysUser;
import com.futhead.restful.model.vo.SysUserQueryVo;
import com.futhead.restful.model.vo.SysUserWithRoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    public SysUser saveOrUpdate(SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }

    public void updateSelective(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    public void deleteSysUsersById(int id) {
        sysUserRepository.delete(id);
    }

    public SysUser findSysUserById(int id) {
        return sysUserRepository.findOne(id);
    }

    public List<SysUser> getSysUsers() {
        return sysUserRepository.findAll();
    }

    public Page<SysUserQueryVo> queryByConditions(SysUserQueryVo queryVo) {
        PageHelper.startPage(1, 2);
        return sysUserMapper.queryByConditions(queryVo);
    }

    public SysUser findSysUserByUsername(String username) {
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if (sysUser != null) {
            SysUserWithRoleVo vo = new SysUserWithRoleVo(sysUser);
            vo.setRoles(sysRoleMapper.findRolesByUserId(sysUser.getId()));
        }
        return sysUser;
    }
}
