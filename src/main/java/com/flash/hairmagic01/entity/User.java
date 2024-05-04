package com.flash.hairmagic01.entity;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/3/24 9:55
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String explain;
    private String phoneNumber;
    private String gender;
    private String email;
    private String identity;
    private Integer isvip;
    private Date createTime;
    private Date upateTime;
    private Set<Integer> following = new HashSet<>(); // 初始化 following
    private Set<Integer> followers = new HashSet<>(); // 初始化 followers

}
