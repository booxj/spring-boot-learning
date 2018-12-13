package com.springboot.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

//    @PreAuthorize("hasAuthority('get')")
    @PreAuthorize("hasAnyRole('ADMIN','GUEST')")
    @RequestMapping("/authority.html")
    public String authority() {
        return "authority";
    }

}
