package com.springboot.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map defultExcepitonHandler(Exception e) {
        Map<String, String> map = new HashMap<>();
        if (e instanceof AccessDeniedException) {
            map.put("code", "403");
            map.put("msg", "Forbidden");
        } else {
            map.put("code", "500");
            map.put("msg", "Internal Server Error");
        }
        return map;
    }

}
