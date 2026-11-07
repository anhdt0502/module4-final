package com.fashion.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.fashion.service.*.*(..))")
    public void beforeServiceMethod(JoinPoint joinPoint) {

        System.out.println(
                "[AOP] Bắt đầu: "
                        + joinPoint.getSignature().getName()
        );
    }

    @After("execution(* com.fashion.service.*.*(..))")
    public void afterServiceMethod(JoinPoint joinPoint) {

        System.out.println(
                "[AOP] Kết thúc: "
                        + joinPoint.getSignature().getName()
        );
    }
}