package com.springboot.repeat.commit.single;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BookController.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/14 14:20
 * @version:
 */
//@RestController
@RequestMapping("/single/books")
public class BookController {

    @LocalLock(key = "book:arg[0]")
    @GetMapping
    public String query(@RequestParam String id) {
        return "success";
    }
}
