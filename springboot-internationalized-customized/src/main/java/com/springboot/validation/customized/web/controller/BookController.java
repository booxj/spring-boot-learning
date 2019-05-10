package com.springboot.validation.customized.web.controller;

import com.springboot.validation.customized.domain.i18n.Book;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BookController.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/9 18:51
 * @version:
 */
@Validated
@RestController
@RequestMapping("book")
public class BookController {

    @GetMapping()
    public Book book() {
        return null;
    }

    @RequestMapping
    public String addBook(@Validated Book book) {
        return "insert success";
    }
}
