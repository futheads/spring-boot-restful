//package com.futhead.restful.security;
//
//import com.futhead.restful.model.po.SysRole;
//import com.futhead.restful.model.po.SysUser;
//import com.futhead.restful.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/8/1.
// */
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    //重写loadUserByUsername方法获得userdetails类型用户
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser user = sysUserService.findSysUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("Username " + username + "not found");
//        }
//        List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
//
//        ///////////////////////////////////这块代码为根据角色控制//////////////////////////////////////////
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        for (SysRole role : user.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        ///////////////////////////////////这块代码为根据角色控制//////////////////////////////////////////
//
//        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
//    }
//
//}
