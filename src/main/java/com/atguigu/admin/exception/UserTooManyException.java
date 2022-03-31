package com.atguigu.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户数量太多啦") //返回一个状态码信息
public class UserTooManyException extends RuntimeException{

    public UserTooManyException(){

    }
    public UserTooManyException(String message){
        super(message);
    }
}
