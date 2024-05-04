package com.flash.hairmagic01.entity;

import lombok.Data;

import java.util.Date;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/27 9:20
 */
@Data
public class Order {
    private int id;
    private String name;
    private String phoneNumber;
    private int clientId;
    private int merchantId;
    private int number;
    private float price;
    private String status;
    private Date orderTime;
    private Date createTime;
}
