package com.springboot.jwt;

import com.springboot.jwt.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JwtApplication {

    public final static Map<String, User> USER_MAP = new HashMap<>();

    {
        User user = new User("admin", "admin");
        USER_MAP.put(user.getUsername(), user);
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }
}
