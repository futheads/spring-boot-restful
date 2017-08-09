package com.futhead.restful.dao;

import com.futhead.restful.model.po.SysUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by futhead on 2017/7/30.
 */
@Component
@CacheConfig(cacheNames = "users")
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByUsername(String username);

    @Cacheable(key = "#p0")
    SysUser findOne(int id);

    @CacheEvict(key = "#p0")
    void delete(Integer integer);

    @CachePut(key = "#p0.id")
    SysUser save(SysUser sysUser);

}
