package com.example.shoppingmall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class DebuggingAspect {

    @Pointcut("@annotation(com.example.shoppingmall.aop.annotation.RunningTime)")
    private void enableRunningTime(){}

    @Pointcut("execution(* com.example.shoppingmall.api.*.*(..))")
    private void controllerPointcut(){}

    @Around("enableRunningTime()")
    public Object loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object returningObh = joinPoint.proceed();
        stopWatch.stop();

        String methodName = joinPoint.getSignature().getName();

        log.info("'{}' total running time : {} sec", methodName, stopWatch.getTotalTimeSeconds());

        return returningObh;
    }

    @Around("controllerPointcut()")
    public Object loggingRunningTimeController(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        StopWatch stopWatch = new StopWatch();
        Map<String, Object> params = new HashMap<>();

        stopWatch.start();
        Object returningObh = joinPoint.proceed();
        stopWatch.stop();

        String methodName = joinPoint.getSignature().getName();
        try {
            params.put("method", joinPoint.getSignature().getName());
            params.put("logTime", LocalDateTime.now().withNano(0));
            params.put("requestURI", request.getRequestURI());
            params.put("http_method", request.getMethod());
        } catch (Exception e) {
            log.error("LoggerAspect error", e);
        }
        log.info("{}", params);
        log.info("'{}' total running time : {} sec\n\n", methodName, stopWatch.getTotalTimeSeconds());

        return returningObh;
    }

}
