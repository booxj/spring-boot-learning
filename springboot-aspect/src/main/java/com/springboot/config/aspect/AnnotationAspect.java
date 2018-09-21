package com.springboot.config.aspect;

import com.springboot.config.Aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class AnnotationAspect {

//    @Around("@annotation(aop)")
//    public Object around(ProceedingJoinPoint joinPoint, Aop aop) throws Throwable {
//        System.out.println("into annotation aspect");
//        return joinPoint.proceed();
//    }

    @Before("@annotation(aop)")
    public void before(JoinPoint joinPoint, Aop aop) throws Throwable {
        System.out.println("into annotation before");
    }

//    @After("@annotation(aop)")
//    public void after(JoinPoint joinPoint, Aop aop) throws Throwable {
//        System.out.println("into annotation after");
//    }

    @AfterReturning("@annotation(aop)")
    public void afterReturning(JoinPoint joinPoint, Aop aop) throws Throwable {
        System.out.println("into annotation after");
    }
}
