package com.flash.hairmagic01.exception;

import com.flash.hairmagic01.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/3/24 12:23
 */
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e){
        return Result.error(e.getCode() ,e.getMessage());
    }
}
