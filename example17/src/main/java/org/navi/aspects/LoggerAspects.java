package org.navi.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspects {
    @Around(*execution(*.com.navi.*.*(...))*)
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        //aspect logic
    }
}
