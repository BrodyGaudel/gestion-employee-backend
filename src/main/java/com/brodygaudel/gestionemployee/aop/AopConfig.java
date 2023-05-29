package com.brodygaudel.gestionemployee.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AopConfig {

    @Before("execution(* com.brodygaudel.gestionemployee.services.implementation.EmployeeServiceImpl.*(..))")
    public void logMethodsEmployeeServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    @Before("execution(* com.brodygaudel.gestionemployee.services.implementation.FormationServiceImpl.*(..))")
    public void logMethodsFormationServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    @Before("execution(* com.brodygaudel.gestionemployee.services.implementation.ExperienceServiceImpl.*(..))")
    public void logMethodsExperienceServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    @Before("execution(* com.brodygaudel.gestionemployee.services.implementation.AbsenceServiceImpl.*(..))")
    public void logMethodsAbsenceServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    private void logger(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("In method : "+name+":");
    }


}
