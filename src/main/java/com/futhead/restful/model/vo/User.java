package com.futhead.restful.model.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/11.
 */
public class User implements Serializable {

    private String name;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
