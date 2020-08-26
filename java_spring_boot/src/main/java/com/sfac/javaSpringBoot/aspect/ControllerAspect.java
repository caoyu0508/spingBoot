package com.sfac.javaSpringBoot.aspect;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {
    private final  static Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);
    //设置切到哪里去 扫描包，让包下带有注解ServiceAnnotation的方法交给AOP切面进行管理
    /**
     * 关联在方法上的切点
     * 第一个*代表返回类型不限
     * 第二个*代表module下所有子包
     * 第三个*代表所有类
     * 第四个*代表所有方法
     * (..) 代表参数不限
     * Order 代表优先级，数字越小优先级越高
     */
    @Pointcut("execution(public * com.sfac.javaSpringBoot.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut(){

    }

    //前置通知
    @Before(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinpoint){
        LOGGER.debug("=========this is before controller========");
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        LOGGER.debug("请求来源："+request.getRemoteAddr());
        LOGGER.debug("请求url："+request.getRequestURL().toString());
        LOGGER.debug("请求方法："+request.getMethod());
        LOGGER.debug("响应方法："+joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName());
        LOGGER.debug("请求参数："+ Arrays.toString(joinpoint.getArgs()));
        LOGGER.debug(SecurityUtils.getSubject().isRemembered() +
                "--" + SecurityUtils.getSubject().isAuthenticated());
    }

    //环绕通知
    @Around(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("==========this is around controller");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

    }

    //后置通知
    @After(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController() {
        LOGGER.debug("==== This is after controller ====");
    }
}
