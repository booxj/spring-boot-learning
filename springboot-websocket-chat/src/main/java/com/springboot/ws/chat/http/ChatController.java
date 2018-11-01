package com.springboot.ws.chat.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {

    @GetMapping({"login.html", "login"})
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String loginAction() {
        return "";
    }

    @GetMapping({"chat.html", "chat"})
    public String chat() {
        return "chat";
    }
}
