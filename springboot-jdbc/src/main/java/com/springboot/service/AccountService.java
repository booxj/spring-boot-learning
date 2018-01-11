package com.springboot.service;

import com.springboot.modal.Account;

import java.util.List;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:26
 * @see:
 * @since:
 */
public interface AccountService {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
