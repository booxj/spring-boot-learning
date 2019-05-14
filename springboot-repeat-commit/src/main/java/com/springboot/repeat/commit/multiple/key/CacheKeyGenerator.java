package com.springboot.repeat.commit.multiple.key;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CacheKeyGenerator {

    String getLockKey(ProceedingJoinPoint pjp);
}
