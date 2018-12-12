package com.springboot.security.service.impl;

import com.springboot.security.entity.SysUser;
import com.springboot.security.mapper.UserMapper;
import com.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(cacheNames = "authority", key = "#username")
    @Override
    public SysUser getUserByName(String username) {
        return userMapper.selectByName(username);
    }
}
