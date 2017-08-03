package com.futhead.restful.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/7/30.
 */
@Entity
public class SysRoleUser {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int SysUserId;

    @Column(nullable = false)
    private int SysRoleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSysUserId() {
        return SysUserId;
    }

    public void setSysUserId(int sysUserId) {
        SysUserId = sysUserId;
    }

    public int getSysRoleId() {
        return SysRoleId;
    }

    public void setSysRoleId(int sysRoleId) {
        SysRoleId = sysRoleId;
    }

}
