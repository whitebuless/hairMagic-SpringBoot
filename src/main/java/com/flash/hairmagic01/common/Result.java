package com.flash.hairmagic01.common;

import lombok.*;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/3/24 9:11
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR = "500";

    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(CODE_SUCCESS,"请求成功",null);
    }

    public static Result success(Object data){
        return new Result(CODE_SUCCESS,"请求成功",data);
    }

    public static Result error(String msg){
        return new Result(CODE_SYS_ERROR,msg,null);
    }

    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }

    public static Result error(){
        return new Result(CODE_SYS_ERROR,"系统错误",null);
    }
}
