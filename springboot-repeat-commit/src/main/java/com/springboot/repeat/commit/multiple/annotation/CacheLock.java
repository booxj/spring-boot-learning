package com.springboot.repeat.commit.multiple.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {

    /**
     * redis key 前缀
     * @return
     */
    String prefix() default "";

    int expire() default 5;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 分隔符
     * @return
     */
    String delimiter() default ":";
}

