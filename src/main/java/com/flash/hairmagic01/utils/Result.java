package com.flash.hairmagic01.utils;

//import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回前端页面结果，包含是否登录成功，访问的信息，数据
 */
public class Result {
    /**
     * 成功
     */
    public static final int SUCCESS = 200;
    /**
     * 服务器逻辑错误
     */
    public static final int SERVE_ERROR = 500;
    public static final String SERVE_ERROR_MSG = "服务器内部异常";
    /**
     * 重复登录
     */
    public static final int REPEAT_LOGIN = 506;
    public static final String REPEAT_LOGIN_MSG = "您已在其他位置登录。是否重新登录？";
    /**
     * 用户名密码错误
     */
    public static final int LOGIN_ERROR = 507;
    public static final String LOGIN_ERROR_MSG = "用户名或密码错误";

    /**
     * 操作数据库错误
     */
    public static final int OPERATION_DB_ERROR = 508;
    public static final String OPERATION_DB_ERROR_MSG = "操作异常";
    /**
     * 图片不存在
     */
    public static final int IMG_EXIST_ERROR = 509;
    public static final String IMG_EXIST_ERROR_MSG = "上传图片不存在";

    /**
     * 是否访问成功
     */
    private boolean success;
    /**
     * 成功与否的信息
     */
    private String message;
    /**
     * 状态码
     */
    private int code;

    /**
     * 携带数据
     */
    private Object data;
    /**
     * 判断是否已登录
     */
    private boolean login;
    /**
     * 未登录的构造方法
     */
    public Result() {
        this.login = false;
    }

    private Result(boolean success,int code, String message, Object data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
        this.login = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public boolean isLogin() {
        return login;
    }

    public static Result success(){
        return new Result(true,SUCCESS,null,null);
    }
    public static Result success(String message){
        return new Result(true,SUCCESS,message,null);
    }
    public static Result success(String message, Object data){
        return new Result(true,SUCCESS,message,data);
    }
    public static Result fail(int code){
        return new Result(false,code,null,null);
    }
    public static Result fail(int code,String message){
        return new Result(false,code,message,null);
    }
    public static Result fail(int code,String message, Object data){
        return new Result(false,code,message,data);
    }
}