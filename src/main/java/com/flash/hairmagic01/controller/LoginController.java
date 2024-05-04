package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import com.flash.hairmagic01.utils.MailMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/29 11:18
 */
@CrossOrigin
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private MailMsg mailMsg;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "sendEmail/{email}")
    public Result sendCode(@PathVariable String email) throws MessagingException {
        log.info("邮箱码：{}",email);
        //从redis中取出验证码信息
        String code = redisTemplate.opsForValue().get(email);
        if (!StringUtils.isEmpty(code)) {
            return Result.success(email + ":" + code + "已存在，还未过期");
        }
        boolean b = mailMsg.mail(email);
        if (b) {
            return Result.success("验证码发送成功！");
        }
        return Result.error("邮箱不正确或为空！");
    }
}
