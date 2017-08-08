package com.futhead.restful.api;

import com.futhead.restful.exception.MyException;
import com.futhead.restful.model.po.SysUser;
import com.futhead.restful.model.vo.User;
import com.futhead.restful.service.SysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by futhead on 2017-7-26.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static final String SUCCESS = "success";

    @Autowired
    private SysUserService sysUserService;

    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @GetMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello" + name;
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/")
    public List<SysUser> getUsers() {
        return sysUserService.getSysUsers();
    }


    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "sysUser", value = "系统用户实体sysUser", required = true, dataType = "SysUser")
    @PostMapping("/")
    public SysUser save(@RequestBody SysUser sysUser) {
//        users.put(user.getId(), user);
        return sysUserService.saveOrUpdate(sysUser);
    }


    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }


    @ApiOperation(value = "更新用户的详细信息", notes = "根据url的id来指定更新对象，并根据传过来的用户信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User", paramType = "body")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return SUCCESS;
    }


    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return SUCCESS;
    }

}
