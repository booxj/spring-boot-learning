package com.springboot.repeat.commit.multiple;

import com.google.common.base.Strings;
import com.springboot.repeat.commit.multiple.annotation.CacheLock;
import com.springboot.repeat.commit.multiple.key.CacheKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @ClassName: LockMethodInterceptor.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/14 14:42
 * @version:
 */
@Configuration
@Aspect
public class LockMethodInterceptor {

    @Autowired
    public LockMethodInterceptor(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
        this.redisLockHelper = redisLockHelper;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    private final RedisLockHelper redisLockHelper;
    private final CacheKeyGenerator cacheKeyGenerator;

    @Around("execution(public * *(..)) && @annotation(com.springboot.repeat.commit.multiple.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (Strings.isNullOrEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }

        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
            if (!success) {
                throw new RuntimeException("重复提交");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
//            redisLockHelper.unlock(lockKey, value);
        }
    }
}
