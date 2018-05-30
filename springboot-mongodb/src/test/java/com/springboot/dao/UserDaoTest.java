package com.springboot.dao;

import com.springboot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setId(2l);
        user.setUsername("小明");
        user.setPassword("fffooo123");
        userDao.saveUser(user);
    }

    @Test
    public void findUserByUserName() {
        User user = userDao.findUserByUserName("小明");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2l);
        user.setUsername("天空");
        user.setPassword("fffxxxx");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        userDao.deleteUserById(1l);
    }
}
