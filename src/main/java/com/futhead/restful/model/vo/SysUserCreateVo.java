package com.futhead.restful.model.vo;

/**
 * Created by Administrator on 2017/8/8.
 */
public class SysUserCreateVo {

    private String username;

    private String password;

    private String pickname;

    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPickname() {
        return pickname;
    }

    public void setPickname(String pickname) {
        this.pickname = pickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SysUserCreateVo{" +
                ", username='" + username + '\'' +
                ", pickname='" + pickname + '\'' +
                ", age=" + age +
                '}';
    }
}
