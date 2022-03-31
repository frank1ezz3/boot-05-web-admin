package com.atguigu.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
/**
 * 处理整个web controller的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class}) //异常处理器
    public String handleArithException(Exception e){
        log.error("异常是：{}",e);
        return "login";//视图地址
    }
}
