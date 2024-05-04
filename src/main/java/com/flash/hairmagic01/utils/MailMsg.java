package com.flash.hairmagic01.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/29 11:05
 */

@Component
public class MailMsg {

    @Resource
    private JavaMailSenderImpl mailSender;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public boolean mail(String email) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //生成随机验证码
        String code = CodeGeneratorUtil.generateCode(6);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //设置一个html邮件信息
        helper.setText("<p style='color: blue'>你好，您的验证码为：" + code + "(有效期为一分钟)</p>", true);
        //设置邮件主题名
        helper.setSubject("FlowerPotNet验证码----验证码");
        //发给谁-》邮箱地址
        helper.setTo(email);
        //谁发的-》发送人邮箱
        helper.setFrom("15153696010@163.com");
        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(1));
        mailSender.send(mimeMessage);
        return true;
    }
}