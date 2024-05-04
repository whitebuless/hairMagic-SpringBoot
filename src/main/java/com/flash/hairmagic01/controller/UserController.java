package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import com.flash.hairmagic01.entity.User;
import com.flash.hairmagic01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * 功能：UserController
 * 作者：whitebuless
 * 日期：2024/3/24 10:07
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
/*
* 新增用户信息
* */
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        try{
            userService.insertUser(user);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException)
                return Result.error("插入数据库错误");
            else
                return Result.error("系统错误");
        }
        return Result.success();
    }
    /*
    * 根据电话号码查询用户信息
    * */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return Result.success(userService.login(user));
    }

    @GetMapping("/findbyid")
    public Result findbyid(int id){
        return Result.success(userService.findUserById(id));
    }

    @GetMapping("/login2")
    public Result login2(String email,String code,String identity){
        String codeTrue = redisTemplate.opsForValue().get(email);
        log.info("真实码：{}",codeTrue);
        log.info("传递码：{}",code);
        if(!codeTrue.equals(code)){
            return Result.error("验证码错误");
        }
        return Result.success(userService.login2(email,identity));
    }
    @PostMapping("/update")
    public Result updateUser(@RequestBody User user){
        return Result.success(userService.UpdateUserInfo(user));
    }
}
