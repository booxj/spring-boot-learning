package com.springboot.security.customized.domain;

import java.util.List;

/**
 * @ClassName: SysUser.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/23 11:01
 * @version:
 */
public class SysUser {

    private Integer id;
    private String username;
    private String password;

    private List<SysRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
