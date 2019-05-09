package com.springboot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:SpringBoot2.0之后，SpringBoot默认使用Lettuce实现redis
 * Jedis:直连redis，多线程环境下非线程安全，除非使用连接池，为每个Jedis实例增加物理连接
 * Lettuce:基于Netty的连接实例，可以在多个线程间并发访问，且线程安全，满足多线程环境下的并发访问，同时它是可伸缩的设计
 *
 * @author: wb
 * @data: 2018/1/11 11:02
 * @see:
 * @since:
 */
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
