package org.navi.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspects {

    private Logger logger = Logger.getLogger(LoggerAspects.class.getName());

    @Around(value = "execution(* org.navi.service.*.*(..))") // The String value here is a pointcut to apply the aspect advice @Around

    /*  Join Point: A join point is a specific point in the application such as method execution, exception handling,
        changing object variable values, etc. In Spring AOP a join point is always the execution of a method. */

    public void log(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info(joinPoint.getSignature().toString() + "Method Execution Start");
        Instant start = Instant.now(); // Instant is from Time class, start is taking note of time of Starting the method.
        joinPoint.proceed();           // --> JoinPoint is the point from the Apect logger to the method/target Method.
        Instant finish = Instant.now(); // Again noting the time of ending of execution of the target method.
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute the method " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + "Method execution end.");

    }
}
