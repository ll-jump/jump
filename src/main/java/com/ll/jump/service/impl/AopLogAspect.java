package com.ll.jump.service.impl;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 〈日志切面〉
 *
 * @author LiLin
 * @date 2020/1/16 0016
 */
public class AopLogAspect {
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("log start:" + joinPoint.getSignature().getName());
        Object o = joinPoint.proceed();
        System.out.println(o.toString());
        System.out.println("log end:" + joinPoint.getSignature().getName());
        return  o;
    }
}