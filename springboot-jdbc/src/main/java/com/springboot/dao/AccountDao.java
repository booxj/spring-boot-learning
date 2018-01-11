package com.springboot.dao;

import com.springboot.modal.Account;

import java.util.List;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:16
 * @see:
 * @since:
 */
public interface AccountDao {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
