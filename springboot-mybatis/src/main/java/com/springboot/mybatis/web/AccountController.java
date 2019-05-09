package com.springboot.mybatis.web;

import com.springboot.mybatis.model.Account;
import com.springboot.mybatis.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:55
 * @see:
 * @since:
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("list")
    public List<Account> getAccounts() {
        return accountService.findAccountList();
    }

    @GetMapping("{id}")
    public Account getAccountById(@PathVariable("id") int id) {
        return accountService.findAccount(id);
    }

    @PutMapping("{id}")
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        int t = accountService.update(name, money, id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable(value = "id") int id) {
        int t = accountService.delete(id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    @PostMapping("")
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {

        int t = accountService.add(name, money);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }
}
