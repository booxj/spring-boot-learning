package com.springboot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 11:08
 * @see:
 * @since:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

    public static Logger logger= LoggerFactory.getLogger(RedisDaoTest.class);

    @Autowired
    RedisDao redisDao;

    @Test
    public void testRedis(){
        redisDao.setKey("name","booxJ");
        redisDao.setKey("age","27");
        logger.info(redisDao.getValue("name"));
        logger.info(redisDao.getValue("age"));
    }
}
