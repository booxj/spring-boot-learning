package com.springboot.security.service;


import com.springboot.security.entity.SysUser;

public interface UserService {

    /**
     * 根据用户名获取系统用户
     */
    SysUser getUserByName(String username);

}
