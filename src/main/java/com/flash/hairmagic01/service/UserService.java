package com.flash.hairmagic01.service;

import com.flash.hairmagic01.entity.User;
import com.flash.hairmagic01.exception.ServiceException;
import com.flash.hairmagic01.mapper.UserMapper;
import com.flash.hairmagic01.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 功能：UserService
 * 作者：whitebuless
 * 日期：2024/3/24 10:05
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public void insertUser(User user){
        userMapper.insert(user);
    }
//    校验邮箱和密码登录
    public User login(User user){
        User dbUser=userMapper.findUserByEmail(user.getEmail());
        if(dbUser==null){
            throw new ServiceException("账号不存在");
        }
        if(!dbUser.getPassword().equals(encryptPassword(user.getPassword()))){
            throw  new ServiceException("密码错误");
        }
        return dbUser;
    }
//    根据id查询用户信息
    public User findUserById(int id){
        User dbUser=userMapper.findUserById(id);
        if(dbUser==null){
            throw new ServiceException("用户不存在");
        }
        return dbUser;
    }
//    通过验证码登录
    public User login2(String email,String identity){
        User dbUser=userMapper.findUserByEmail(email);
        if(dbUser==null){
            String username=RandomStringGenerator.generateRandomString(8);
            String explain="这个人很懒，什么都没有说";
            String gender="男";
            // 创建一个User对象并设置属性
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setExplain(explain);
            newUser.setGender(gender);
            newUser.setEmail(email);
            newUser.setIdentity(identity);
            // 调用Mapper的insert方法插入新用户
            userMapper.insert(newUser);
            User dbUserNew=userMapper.findUserByEmail(email);
            return dbUserNew;
        }else{
            return dbUser;
        }
    }
//    更新用户信息
    public User UpdateUserInfo(User user){
        User dbUser=userMapper.findUserById(user.getId());
        if(dbUser==null){
            throw new ServiceException("用户不存在");
        }
        // 遍历User类的所有属性
        // 遍历User类的所有属性
        // 遍历User类的所有属性
        for (Field field : User.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                // 获取user对象的属性值
                Object value = field.get(user);
                // 如果属性值不为null，则更新dbUser对象的相应属性值
                if (value != null) {
                    // 特殊处理密码属性
                    if (field.getName().equals("password")) {
                        // 对密码进行加密处理再设置给dbUser对象
                        String encryptedPassword = encryptPassword(value.toString());
                        field.set(dbUser, encryptedPassword);
                    } else {
                        // 其他属性直接赋值
                        field.set(dbUser, value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        userMapper.updateUser(dbUser);
        return dbUser;
    }

    // 加密密码方法
    private String encryptPassword(String password) {
        try {
            // 创建MD5加密算法实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对密码进行加密
            byte[] encryptedBytes = md.digest(password.getBytes());
            // 将加密后的字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : encryptedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
