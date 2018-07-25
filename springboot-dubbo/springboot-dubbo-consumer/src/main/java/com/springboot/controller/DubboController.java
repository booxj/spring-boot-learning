package com.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.model.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboController {

    @Reference(version = "1.0.0")
    private UserService userService;


    @GetMapping("/dubbo")
    public String dubbo() {
        User user = userService.getUser();
        return user.toString();
    }


    @GetMapping("/")
    public String welcome(){
        return "welcome!";
    }
}
