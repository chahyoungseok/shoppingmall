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

    @Pointcut("execution(* com.example.shoppingmall..*.*(..))")
    private void cut(){}

    @Around("cut() && enableRunningTime()") // 이처럼 만들어두면 메서드에 @RunningTime만 있어도 시간을 잴 수 있음.
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object returningObh = joinPoint.proceed();

        stopWatch.stop();

        String methodName = joinPoint.getSignature().getName();

        log.info("{} total running time : {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }

    @Before("cut()") // 해당 cut()의 Pointcut을 기준으로 이전에 이 메서드가 실행된다.
    public void loggingArgs(JoinPoint joinPoint) { // cut()의 대상 메서드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메서드명
        String methodName = joinPoint.getSignature()
                .getName();

        for (Object obj : args) {
            log.info("{}#{} inputValue => : {}", className, methodName, obj);
        }
    }
}
