package com.imooc.controller.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

/**
 * @creatdate 2019-05-21 21:09
 */
@Component
@Aspect
public class HttpAspect {

    private  final  static Logger log = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(public * com.imooc.controller.MyBatisCRUDController.*(..))")
    public void log(){

    }

    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        log.info("url={}", request.getRequestURL());
        log.info("mothod={}", request.getMethod());
        log.info("class_mothod={}",joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName() );
        log.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void after(){
        log.info("kidfn");
    }

    @AfterReturning(pointcut = "log()",returning = "Object")
    public void afterreturn(Object Object){
            log.info("response={}",Object.toString());
    }
}
