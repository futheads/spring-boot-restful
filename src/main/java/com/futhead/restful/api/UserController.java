package com.futhead.restful.api;

import com.futhead.restful.model.po.SysUser;
import com.futhead.restful.model.vo.SysUserCreateVo;
import com.futhead.restful.model.vo.SysUserQueryVo;
import com.futhead.restful.service.SysUserService;
import com.futhead.restful.util.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by futhead on 2017-7-26.
 */
@Api("用户控制器")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static final String SUCCESS = "success";

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/")
    public List<SysUser> getUsers() {
        return sysUserService.getSysUsers();
    }

    @ApiOperation(value = "创建用户", notes = "根据SysUserCreateVo对象创建用户")
    @PostMapping("/")
    public SysUser save(@RequestBody SysUserCreateVo sysUserCreateVo) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(sysUserCreateVo.getUsername());
        sysUser.setPassword(sysUserCreateVo.getPassword());
        sysUser.setPickname(sysUserCreateVo.getPickname());
        sysUser.setAge(sysUserCreateVo.getAge());
        return sysUserService.saveOrUpdate(sysUser);
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Int", paramType = "path")
    @GetMapping("/{id}")
    public SysUser getUser(@PathVariable int id) {
        return sysUserService.findSysUserById(id);
    }

    @ApiOperation(value = "更新用户的详细信息", notes = "根据url的id来指定更新对象，并根据传过来的用户信息来更新用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Int", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable int id, @RequestBody SysUser sysUser) {
        sysUserService.updateSelective(sysUser);
        return SUCCESS;
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Int", paramType = "path")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        sysUserService.deleteSysUsersById(id);
        return SUCCESS;
    }

    @ApiOperation(value = "查询用户列表", notes = "根据条件查询系统用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pickname", value = "昵称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "role", value = "角色", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "age", value = "年龄", dataType = "Int", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code=401, message="请求的接口未授权"),
    })
    @GetMapping("/getSysUsers")
    public PageInfo<SysUserQueryVo> queryByConditions(@RequestParam(value = "username", required = false) String username,
                                                      @RequestParam(value = "pickname", required = false) String pickname,
                                                      @RequestParam(value = "role", required = false) String role,
                                                      @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
        SysUserQueryVo sysUserQueryVo = new SysUserQueryVo();
        sysUserQueryVo.setUsername(username);
        sysUserQueryVo.setPickname(pickname);
        sysUserQueryVo.setRole(role);
        sysUserQueryVo.setAge(age);
        PageInfo<SysUserQueryVo> pageInfo = new PageInfo<>(sysUserService.queryByConditions(sysUserQueryVo));
        return pageInfo;
    }

}
