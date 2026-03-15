package com.telusko.spring_boot_rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);


    //Before the class execute
    @Before("execution(* com.telusko.spring_boot_rest.service.JobService.*(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method Called " + jp.getSignature().getName());
    }


    //After the class ge executed
    @After("execution(* com.telusko.spring_boot_rest.service.JobService.getJob*(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob*(..))")
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("Method Executed " + jp.getSignature().getName());
    }

    //When there is  error
    @AfterThrowing("execution(* com.telusko.spring_boot_rest.service.JobService.getJob*(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob*(..))")
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info("Method Crash " + jp.getSignature().getName());
    }

    //Create log when returning from the method call
    @AfterReturning("execution(* com.telusko.spring_boot_rest.service.JobService.getJob*(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob*(..))")
    public void logMethodExecutedSuccess(JoinPoint jp){
        LOGGER.info("Method has executed successfully " + jp.getSignature().getName());
    }
}