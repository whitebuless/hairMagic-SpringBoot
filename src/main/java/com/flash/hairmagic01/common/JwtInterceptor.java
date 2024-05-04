package com.flash.hairmagic01.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.flash.hairmagic01.entity.User;
import com.flash.hairmagic01.exception.ServiceException;
import com.flash.hairmagic01.mapper.UserMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：JwtInterceptor
 * 作者：whitebuless
 * 日期：2024/3/24 11:38
 */
public class JwtInterceptor {
    @Resource
    private UserMapper userMapper;

    public boolean preHandler(HttpServletRequest request, HttpServletResponse response ,Object handler){
        String token=request.getHeader("token"); //header里传来的参数
        if(StrUtil.isBlank(token)){
            token=request.getParameter("token");//url参数，parameter
        }
//        执行认证
        if(StrUtil.isBlank((token))){
            throw new ServiceException("401","请登录");
        }
//        获取token中的userID
        String userId;
        try{
            userId= JWT.decode(token).getAudience().get(0);
        }catch(JWTDecodeException j){
            throw new ServiceException("401","请登录");
        }
//        根据token中的userId查询数据库
        User user=userMapper.findUserById(Integer.valueOf(userId));
        if(user==null){
            throw new ServiceException("401","请登录");
        }
//        用户密码加签验证token
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);
        }catch(JWTVerificationException e){
            throw new ServiceException("401","请登录");
        }
        return true;
    }

}
