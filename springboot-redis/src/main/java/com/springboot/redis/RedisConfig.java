//package com.springboot.redis;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.util.StringUtils;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//public class RedisConfig {
//
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private Integer port;
//    @Value("${spring.redis.password}")
//    private String password;
//    @Value("${spring.redis.timeout}")
//    private Integer timeout;
//
//
//    @Value("${spring.redis.pool.max-wait}")
//    private Integer maxWaitMillis;
//    @Value("${spring.redis.pool.max-total}")
//    private Integer maxTotal;
//    @Value("${spring.redis.pool.min-idle}")
//    private Integer minIdle;
//    @Value("${spring.redis.pool.max-idle}")
//    private Integer maxIdle;
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis.pool")
//    public JedisPoolConfig getRedisConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        return jedisPoolConfig;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    public JedisConnectionFactory getConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//
//        factory.setPoolConfig(jedisPoolConfig);
//        factory.setHostName(host);
//        factory.setPort(port);
//
//        //如果Redis设置有密码
//        if (!StringUtils.isEmpty(password)) {
//            factory.setPassword(password);
//        }
//        //客户端超时时间(ms)
//        factory.setTimeout(timeout);
//        return factory;
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
//        return redisTemplate;
//    }
//
//    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
//        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        // 开启事务
//        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.setConnectionFactory(factory);
//    }
//
//    @Bean(name = "redisUtils")
//    public RedisUtils redisUtils(RedisTemplate<String, Object> redisTemplate) {
//        RedisUtils redisUtils = new RedisUtils();
//        redisUtils.setRedisTemplate(redisTemplate);
//        return redisUtils;
//    }
//}
