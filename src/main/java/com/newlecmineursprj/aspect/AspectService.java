package com.newlecmineursprj.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class AspectService {

    @Around("@annotation(PerfLogger)")
    public Object perfLogAspect(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        log.info("Method name = [{}]",pjp.getSignature().getName());
        logExecutionTime(System.currentTimeMillis() - begin);
        return retVal;
    }

    private void logExecutionTime(long executionTimeMillis) {
        double executionTime = executionTimeMillis / 1000.0;
        log.info("Execution time: {}s", String.format("%.3f", executionTime));
    }
}
