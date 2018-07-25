package com.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.model.User;
import com.springboot.service.UserService;

@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return new User(1, "booxj");
    }
}
