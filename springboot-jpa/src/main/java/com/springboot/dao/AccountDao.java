package com.springboot.dao;

import com.springboot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:42
 * @see:
 * @since:
 */
public interface AccountDao extends JpaRepository<Account,Integer> {
}
