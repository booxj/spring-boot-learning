package com.springboot.repeat.commit.multiple;

import com.springboot.repeat.commit.multiple.annotation.CacheLock;
import com.springboot.repeat.commit.multiple.annotation.CacheParam;
import com.springboot.repeat.commit.single.LocalLock;
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
@RestController
@RequestMapping("/multiple/books")
public class BookController {

    @CacheLock(prefix = "books")
    @GetMapping
    public String query(@RequestParam String id) {
        return "success";
    }
}
