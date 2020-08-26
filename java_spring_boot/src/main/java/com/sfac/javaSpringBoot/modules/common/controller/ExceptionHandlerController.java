package com.sfac.javaSpringBoot.modules.common.controller;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {
    //局部的异常处理，异常处理的名称为AuthorizationException
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result<String> handle403(){
        return new Result<>(Result.ResultStatus.fAILD.status,"","/common/403");
    }
}
