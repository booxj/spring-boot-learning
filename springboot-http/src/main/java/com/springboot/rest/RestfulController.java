package com.springboot.rest;

import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RestfulController.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/15 9:12
 * @version:
 */
@RestController
@RequestMapping("rest")
public class RestfulController {

    @GetMapping("/{id}")
    public String get(@PathVariable String id,
                      @RequestParam(required = false) String name) {
        return String.format("get method:id=%s,name=%s", id, name);
    }


    @PostMapping
    public String post(@RequestParam(required = false) String id,
                       @RequestParam(required = false) String name) {
        return String.format("post method:id=%s,name=%s", id, name);
    }

    @PutMapping("/{id}")
    public String put(@PathVariable String id,
                      @RequestParam(required = false) String name) {
        return String.format("put method:id=%s,name=%s", id, name);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return String.format("delete method:id=%s", id);
    }
}
