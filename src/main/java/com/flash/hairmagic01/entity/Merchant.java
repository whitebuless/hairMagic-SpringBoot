package com.flash.hairmagic01.entity;

import lombok.Data;

import java.util.Date;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/16 19:23
 */
@Data
public class Merchant {
    private int id;
    private String name;
    private int userId;
    private String location;
    private String phoneNumber;
    private String description;
    private float rank;
    private Date creatTime;
}
