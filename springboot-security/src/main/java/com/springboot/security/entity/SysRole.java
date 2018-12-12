package com.springboot.security.entity;

import java.io.Serializable;
import java.util.List;

public class SysRole implements Serializable {

    private Long rid;
    private String rolename;
    private List<SysPermission> permissionList;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
