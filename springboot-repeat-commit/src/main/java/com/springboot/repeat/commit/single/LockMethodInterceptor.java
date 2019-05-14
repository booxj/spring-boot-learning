package com.springboot.repeat.commit.single;

import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LockMethodInterceptor.java
 * @Description: 重复提交拦截器
 * @Author: booxj
 * @CreateDate 2019/5/14 14:06
 * @version:
 */
@Aspect
//@Configuration
public class LockMethodInterceptor {

    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(1000)
            // 设置写缓存后 5 秒钟过期
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    @Around("execution(public * *(..)) && @annotation(com.springboot.repeat.commit.single.LocalLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();

            LocalLock localLock = method.getAnnotation(LocalLock.class);
            String key = getKey(localLock.key(), pjp.getArgs());
            
            if (!Strings.isNullOrEmpty(key)) {
                if (CACHES.getIfPresent(key) != null) {
                    throw new RuntimeException("请勿重复请求");
                }
                // 如果是第一次请求,就将 key 当前对象压入缓存中
                CACHES.put(key, key);
            }

            return  pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
        }
        return null;
    }

    /**
     * 根据注解解析key："book:arg[0]"->"book:1"
     * @param keyExpress
     * @param args
     * @return
     */
    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }
}
