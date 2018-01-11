package com.springboot.web;

import com.springboot.dao.AccountDao;
import com.springboot.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:43
 * @see:
 * @since:
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    @GetMapping("list")
    public List<Account> getAccounts() {
        return accountDao.findAll();
    }

    @GetMapping("{id}")
    public Account getAccountById(@PathVariable("id") int id) {
        return accountDao.findOne(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id,
                                @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        Account account1 = accountDao.saveAndFlush(account);

        return account1.toString();

    }

    @PostMapping("")
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        Account account1 = accountDao.save(account);
        return account1.toString();

    }
}
