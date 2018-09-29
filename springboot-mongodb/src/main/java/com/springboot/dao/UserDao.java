package com.springboot.dao;

import com.springboot.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    User findUserByUserName(String userName);

    List<User> findByPage(int pageNo,int pageSize);

    void updateUser(User user);

    void deleteUserById(Long id);
}
