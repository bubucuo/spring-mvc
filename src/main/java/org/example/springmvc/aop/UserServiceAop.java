package org.example.springmvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UserServiceAop {
    @Pointcut("execution(* org.example.springmvc.service.UserService.login(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        // 在目标方法执行之后执行的代码
        log.info("准备登录");
    }

    /**
     * @After注解的方法会在目标方法执行之后执行，无论是否抛出异常。
     * @param joinPoint
     */
    @After("serviceMethods()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        // 在目标方法执行之后执行的代码
        log.info("登录完成");
    }

    /**
     * @AfterReturning注解的方法会在目标方法正常返回之后执行 如果目标方法抛出异常，@AfterReturning注解的方法不会被触发
     * @param joinPoint
     */
    @AfterReturning("serviceMethods()")
    public void afterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        // 在目标方法正常返回之后执行的代码
        log.info("登录AfterReturning");
    }

    @AfterThrowing("serviceMethods()")
    public void AfterThrowing(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        // 在目标方法抛出异常之后执行的代码
        log.info("目标方法抛出异常");
    }

    @Around("serviceMethods()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取方法入参
        Object[] args = proceedingJoinPoint.getArgs();
        // 在目标方法执行之前和之后都可以执行的代码，可以决定是否继续执行目标方法或直接返回结果。
        log.info("方法执行环绕 前");
        Object res =  proceedingJoinPoint.proceed(); // 继续执行目标方法或直接返回结果。
        log.info("方法执行环绕 后");
        return res;
    }
}
