package com.practice.monitor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class MeasureTimeAdvice {
    @Around("@annotation(com.practice.monitor.MeasureTime)")
    public Object measureTime(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object object = point.proceed();
        stopWatch.stop();
        log.info("Time taken by " + point.getSignature().getName() + "() method is "
                + stopWatch.getTotalTimeMillis() + " ms" + " OR it took in seconds: "+ stopWatch.getTotalTimeSeconds());
        return object;
    }
}