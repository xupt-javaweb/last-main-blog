package com.mh.blog.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    /*输出一串日志记录，生成日志记录器*/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*通过该注解表明该方法是一个切面*/
    @Pointcut("execution(* com.mh.blog.web.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(){
        logger.info("----------doBefore------------");
    }
    @After("log()")
    public void doAfter(){
        logger.info("-----------doAfter------------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result : {}"+result);
    }
}
