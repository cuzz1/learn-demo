package com.cuzz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect  // 表示该类是一个通知类
public class MyAdvice {
    
    // 前置通知
    @Before("execution(* com.cuzz.service..*ServiceImpl.*(..))")
    public void before(){
        System.out.println("这是前置通知!!");
    }

    // 后置通知
    @AfterReturning("execution(* com.cuzz.service..*ServiceImpl.*(..))")
    public void afterReturning(){
        System.out.println("这是后置通知(如果出现异常不会调用)!!");
    }
    // 环绕通知
    @Around("execution(* com.cuzz.service..*ServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("这是环绕通知之前的部分!!");
        // 调用目标方法
        Object proceed = pjp.proceed();
        System.out.println("这是环绕通知之后的部分!!");
        return proceed;
    }
    // 异常通知
    @AfterThrowing("execution(* com.cuzz.service..*ServiceImpl.*(..))")
    public void afterException(){
        System.out.println("出事啦!出现异常了!!");
    }
    // 后置通知
    @After("execution(* com.cuzz.service..*ServiceImpl.*(..))")
    public void after(){
        System.out.println("这是后置通知(出现异常也会调用)!!");
    }
}