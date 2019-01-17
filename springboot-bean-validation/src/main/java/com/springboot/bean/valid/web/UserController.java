package com.springboot.bean.valid.web;

import com.springboot.bean.valid.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @RequestMapping("user/save")
    public Object save(@Valid User user) {
        return user;
    }
}
