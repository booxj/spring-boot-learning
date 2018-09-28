package com.springboot.search.web;


import com.springboot.search.bean.User;
import com.springboot.search.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/insert")
    public boolean createUser(@RequestBody User user) {
        return userService.insert(user);
    }


    @GetMapping("/search")
    public List<User> search(@RequestParam(value = "searchContent") String searchContent) {
        return userService.search(searchContent);
    }

    @GetMapping("/search/page")
    public List<User> searchUser(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUser(pageNumber, pageSize, searchContent);
    }


    @GetMapping("/search/weight")
    public List<User> searchUserByWeight(@RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUserByWeight(searchContent);
    }


}
