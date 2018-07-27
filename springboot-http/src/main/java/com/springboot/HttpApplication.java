package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpApplication.class, args);
    }


    @GetMapping("get")
    public String get(String p) {
        return "get : " + p;
    }

    @PostMapping("post")
    public String post(String p) throws InterruptedException {
        Thread.sleep(3000);
        return "post : " + p;
    }
}
