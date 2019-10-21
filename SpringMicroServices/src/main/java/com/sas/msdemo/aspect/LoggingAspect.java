package com.sas.msdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(Loggable)")
    public void executeLogging() {
    }

    @Before("executeLogging()")
    public void printLogBefore(JoinPoint joinPoint) {
        StringBuilder message = new StringBuilder("*** Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append("\nArgs: [");
            Arrays.asList(args).forEach(arg -> {
                message.append(arg + "|");
            });
            message.append("]");
        }
        LOGGER.info(message.toString());
    }

    @AfterReturning(pointcut = "executeLogging()", returning = "returnValue")
    public void printLogAfter(Object returnValue) {
        StringBuilder message = new StringBuilder("*** Returns: ");
        if (returnValue instanceof Collection) {
            message.append(((Collection) returnValue).size() + " instance(s)");
        } else {
            message.append(returnValue.toString());
        }
        LOGGER.info(message.toString());
    }

    @Around("executeLogging()")
    public Object timeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = proceedingJoinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        LOGGER.info("***Total time taken is: " + totalTime + " ms");
        return returnValue;
    }
}
