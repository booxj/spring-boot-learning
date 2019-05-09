package com.springboot.mybatis.service;

import com.springboot.mybatis.mapper.AccountMapper;
import com.springboot.mybatis.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:53
 * @see:
 * @since:
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public int add(String name, double money) {
        return accountMapper.add(name, money);
    }

    public int update(String name, double money, int id) {
        return accountMapper.update(name, money, id);
    }

    public int delete(int id) {
        return accountMapper.delete(id);
    }

    public Account findAccount(int id) {
        return accountMapper.findAccount(id);
    }

    public List<Account> findAccountList() {
        return accountMapper.findAccountList();
    }

    public int updateMoney(double money, int id) {
        return accountMapper.updateMoney(money, id);
    }
}
