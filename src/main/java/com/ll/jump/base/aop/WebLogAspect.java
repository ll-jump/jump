package com.ll.jump.base.aop;

import com.google.gson.Gson;

import com.ll.jump.base.result.ServiceResult;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * log aspect
 * 
 * @author LiLin
 * @date 2019/09/16
 * @version 1.0.0
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    private static final String SPLIT = ",";

    private Gson gson = new Gson();


    @Pointcut("execution(public * com.ll.jump.controller.*.*(..))")
    public void log() {

    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        StringBuilder requestBody = new StringBuilder();
        Arrays.asList(args).forEach(arg -> {
            Object logArg = gson.fromJson(gson.toJson(arg), arg.getClass());
            requestBody.append(logArg.toString());
            requestBody.append(SPLIT);
        });
        if (requestBody.length() > 0) {
            requestBody.deleteCharAt(requestBody.length() - 1);
        }
        LOGGER.info("requestBody:【{}】", requestBody.toString());
        ServiceResult result = (ServiceResult)joinPoint.proceed();
        Object data = result.getData();
        String responseBody = "";
        if (data != null) {
            Object logData = gson.fromJson(gson.toJson(data), data.getClass());
            responseBody = logData.toString();
        }
        LOGGER.info("code:【{}】,message:【{}】,responseBody:【{}】", result.getCode(), result.getMessage(), responseBody);
        return result;
    }

}
