package com.futhead.restful.model.vo;

/**
 * Created by futhead on 2017-8-9.
 */
public class SysUserQueryVo {

    private String username;

    private String pickname;

    private String role;

    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPickname() {
        return pickname;
    }

    public void setPickname(String pickname) {
        this.pickname = pickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SysUserQueryVo{" +
                "username='" + username + '\'' +
                ", pickname='" + pickname + '\'' +
                ", role='" + role + '\'' +
                ", age=" + age +
                '}';
    }
}
