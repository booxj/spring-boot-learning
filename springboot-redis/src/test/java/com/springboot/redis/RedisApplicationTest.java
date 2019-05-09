package com.springboot.redis;

import com.springboot.core.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName: RedisApplicationTest.java
 * @Description: TODO
 * @Author: wangbo
 * @CreateDate 2019/5/9 15:25
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTest {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * redis 支持5种数据结构 string,list,hash,set,zset
     */

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void StringTest() {
        String key = "string";

        User user = new User(1, "张三", 10, new Date());
        redisTemplate.opsForValue().set(key, user);
        logger.info("add a user to redis:\n{}", user);

        User redisUser = (User) redisTemplate.opsForValue().get(key);
        logger.info("get the user from redis:\n{}", redisUser);

        assertEquals(user.toString(), redisUser.toString());
        afterTest(key);
    }


    String[] ALPHABET = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    @Test
    public void ListTest() {
        String key = "list";
        redisTemplate.opsForList().rightPushAll(key, ALPHABET);
        logger.info("add alphabet to redis");
        List<Object> list1 = redisTemplate.opsForList().range(key, 0, 2);
        logger.info("{}", list1);
        assertEquals(list1,Arrays.asList(Arrays.copyOf(ALPHABET,3)));
        afterTest(key);
    }

    @Test
    public void HashTest() {
        String key = "map";

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");

        redisTemplate.opsForHash().putAll(key, map);


        logger.info("get hash's keys from redis:{}", redisTemplate.opsForHash().keys(key));
        logger.info("get hash's values from redis:{}", redisTemplate.opsForHash().values(key));

        logger.info("get hash from redis:{}", redisTemplate.opsForHash().entries(key));

        assertEquals(redisTemplate.opsForHash().get(key, "key1"), map.get("key1"));
        afterTest(key);
    }


    @Test
    public void SetTest() {
        String key = "set";
        redisTemplate.opsForSet().add(key, "java", "c++", "php", "python");
        logger.info("get set from redis: {}", redisTemplate.opsForSet().members(key));

        afterTest(key);
    }

    @Test
    public void zSetTest() {
        String key = "zset";
        Random random = new Random(System.nanoTime());
        redisTemplate.opsForZSet().add(key, "Tom", random.nextInt(100));
        redisTemplate.opsForZSet().add(key, "Jack", random.nextInt(100));
        redisTemplate.opsForZSet().add(key, "Mic", random.nextInt(100));

        logger.info("get zset from redis:{}", redisTemplate.opsForZSet().range(key, 0, -1));

        // 正常获取顺序为从小到大，如果希望从大到小需要reverse
        Set<ZSetOperations.TypedTuple<Object>> tuples = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, -1);
        logger.info("get zset from redis with score:");
        tuples.forEach(t -> {
            logger.info("value:{},score:{}", t.getValue(), t.getScore());
        });

        afterTest(key);
    }

    /**
     * 测试后删除缓存
     *
     * @param key
     */
    void afterTest(String key) {
        redisTemplate.delete(key);
    }
}
