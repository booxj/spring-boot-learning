package com.booxj.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 9:37
 * @see:
 * @since:
 */
@RestController
public class MiyaController {

    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private int age;

    @GetMapping("value")
    public String value() {
        return name + ":" + age;
    }
}
