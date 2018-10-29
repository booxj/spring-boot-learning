package com.springboot.websocket.server.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpServer {


    @RequestMapping("http")
    public String http(){
        return "I am a http request";
    }
}
