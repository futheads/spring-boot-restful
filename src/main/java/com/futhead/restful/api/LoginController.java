package com.futhead.restful.api;

/**
 * Created by Administrator on 2017/8/5.
 */
//@RestController
//public class LoginController {
//
//    @GetMapping(value = "/login")
//    @ResponseBody
//    //用户名密码是用Base64加密，原文为admin:admin,即用户名：密码，内容是放在request.getHeader的"authorization"中
//    public Object login(@AuthenticationPrincipal SysUser loginedUser, @RequestParam(name = "logout", required = false) String  logout) {
//        if (logout != null) {
//            return null;
//        }
//        if (loginedUser != null) {
//            return loginedUser;
//        }
//        return null;
//    }
//}
