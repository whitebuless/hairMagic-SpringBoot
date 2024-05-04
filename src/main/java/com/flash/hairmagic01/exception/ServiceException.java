package com.flash.hairmagic01.exception;

import lombok.Getter;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/3/24 12:24
 */
@Getter
public class ServiceException extends RuntimeException{
    private final String code;
    public ServiceException(String msg){
        super(msg);
        this.code="500";
    }
    public ServiceException(String code,String msg){
        super(msg);
        this.code=code;
    }
}
