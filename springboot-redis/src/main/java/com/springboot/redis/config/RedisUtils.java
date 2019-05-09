package com.springboot.redis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisUtils.java
 * @Description: TODO
 * @Author: wangbo
 * @CreateDate 2019/5/9 16:53
 * @version:
 */
@Component
@AutoConfigureAfter(RedisConfiguration.class)
public class RedisUtils implements ApplicationContextAware, InitializingBean {

    private static ApplicationContext applicationContext;

    private static RedisTemplate<String, Object> redisTemplate;

    // TODO: 根据需要封装 RedisTemplate 相关方法

    @Override
    public void afterPropertiesSet() throws Exception {
        this.redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisTemplate");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
