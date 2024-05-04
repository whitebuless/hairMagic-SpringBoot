package com.flash.hairmagic01.mapper;


import com.flash.hairmagic01.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能：user mapper
 * 作者：whitebuless
 * 日期：2024/3/24 10:04
 */
@Mapper
public interface UserMapper {
    @Insert("insert into `user` (username,password,`explain`,phone_number,gender,email,identity,isvip,create_time) " +
            "values (#{username},#{password},#{explain},#{phoneNumber},#{gender},#{email}, #{identity}, #{isvip},NOW())")
    void insert (User user);
    @Select("select * from `user` where phone_number=#{phoneNumber}")
    User findUserByPhoneNumber(User user);
    @Select("select * from `user` where id=#{id}")
    User findUserById(int id);
    @Select("select * from `user` where email=#{email}")
    User findUserByEmail(String email);

    @Update("<script>" +
            "UPDATE `user` " +
            "<set>" +
            "<if test='username != null'>username=#{username},</if>" +
            "<if test='password != null'>password=#{password},</if>" +
            "<if test='explain != null'>`explain`=#{explain},</if>" +
            "<if test='phoneNumber != null'>phone_number=#{phoneNumber},</if>" +
            "<if test='gender != null'>gender=#{gender},</if>" +
            "<if test='email != null'>email=#{email},</if>" +
            "<if test='identity != null'>identity=#{identity},</if>" +
            "<if test='isvip != null'>isvip=#{isvip},</if>" +
            "</set>" +
            "WHERE id=#{id}" +
            "</script>")
    void updateUser(User user);
}
