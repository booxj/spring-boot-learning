package com.springboot.dao;

import com.springboot.model.User;

public interface UserDao {

    void saveUser(User user);

    User findUserByUserName(String userName);

    int updateUser(User user);

    void deleteUserById(Long id);
}
