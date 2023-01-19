package com.example.shoppingmall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class DebuggingAspect {

    @Pointcut("@annotation(com.example.shoppingmall.aop.annotation.RunningTime)")
    private void enableRunningTime(){}

    @Around("enableRunningTime()")
    public Object loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object returningObh = joinPoint.proceed();
        stopWatch.stop();

        String methodName = joinPoint.getSignature().getName();

        log.info("{} total running time : {} sec", methodName, stopWatch.getTotalTimeSeconds());

        return returningObh;
    }
}
