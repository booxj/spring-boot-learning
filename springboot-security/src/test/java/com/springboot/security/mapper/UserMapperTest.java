package com.springboot.security.mapper;

import com.springboot.security.entity.SysUser;
import com.springboot.security.mapper.UserMappercom.springboot.security.SpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends SpringTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserByNameTest() {
        SysUser user = userMapper.selectByName("admin");
        System.out.println(user);
    }
}
