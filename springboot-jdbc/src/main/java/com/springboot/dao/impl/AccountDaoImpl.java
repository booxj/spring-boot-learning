package com.springboot.dao.impl;

import com.springboot.dao.AccountDao;
import com.springboot.modal.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:17
 * @see:
 * @since:
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Account account) {
        return jdbcTemplate.update("INSERT INTO account(name, money) VALUES(?, ?)", account.getName(), account.getMoney());
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE account SET NAME=? ,money=? WHERE id=?", account.getName(), account.getMoney(), account.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM TABLE account WHERE id=?", id);
    }

    @Override
    public Account findAccountById(int id) {
        List<Account> list = jdbcTemplate.query("SELECT * FROM account WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper(Account.class));
        if (list != null && list.size() > 0) {
            Account account = list.get(0);
            return account;
        } else {
            return null;
        }
    }

    @Override
    public List<Account> findAccountList() {
        List<Account> list = jdbcTemplate.query("SELECT * FROM account", new Object[]{}, new BeanPropertyRowMapper(Account.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
}
