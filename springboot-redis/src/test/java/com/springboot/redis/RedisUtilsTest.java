package com.springboot.redis;

import com.springboot.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redis;

    @Test
    public void redisTest() {

        User a = new User(1L,"aa",new Date());
        User b = new User(1L,"aa",new Date());
        User c = new User(1L,"aa",new Date());

        List<User> users = new ArrayList<>();
        users.add(a);
        users.add(b);
        users.add(c);

        redis.set("userA",a);
        redis.set("users",users);

        User u = (User) redis.get("userA");
        List<User> us = (List<User>) redis.get("users");
        System.out.println(redis.get("userA"));
        System.out.println(redis.get("users"));


//        System.out.println("==============object===============");
        redis.set("string", "string",5);
        System.out.println(redis.get("string"));
//
//        System.out.println("==============map===============");
//        Map<String, Object> map = new HashMap<>();
//        map.put("a", 1);
//        map.put("b", 2);
//        redis.hmset("map", map);
//        System.out.println(redis.hmget("map"));
//
//        System.out.println("==============set===============");
//        redis.sSet("set", "aaa", "bbb", "ccc");
//        System.out.println(redis.sGetSetSize("set") + ":" + redis.sGet("set"));
//
//        System.out.println("==============list===============");
//        List<String> list = new ArrayList<>();
//        list.add("aaa");
//        list.add("bbb");
//        list.add("ccc");
//        redis.lSet("list",list);
//        System.out.println(redis.lGetListSize("list") + ":" + redis.lGet("list", 0, 0));
    }
}
