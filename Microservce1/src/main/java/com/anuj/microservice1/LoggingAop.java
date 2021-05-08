package com.anuj.microservice1;


import java.util.logging.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAop {

	private static final Logger log = Logger.getLogger("Mylogger");  

	
    @Pointcut("within(com.anuj.microservice1.*)")
    public void logAllMethodsStartAndEnd()
    {
  
    }
 
    @Around("logAllMethodsStartAndEnd()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable
    {
        log.info(pjp.getTarget().getClass().getSimpleName() + "-" + pjp.getSignature().getName() + "() start");
        Object returnValue = pjp.proceed();
        log.info(pjp.getTarget().getClass().getSimpleName() + "-" + pjp.getSignature().getName() + "() end");
  
        return returnValue;
    }
}
