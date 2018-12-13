package com.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CsrfController {

    @RequestMapping("/csrf/test")
    @ResponseBody
    public String test() {
        return "测试成功";
    }

    @RequestMapping("csrf.html")
    public String csrf() {
        return "csrf";
    }
}
