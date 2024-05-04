package com.flash.hairmagic01.entity;

import lombok.Data;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/17 19:18
 */
@Data
public class Staff {
    private int id;
    private String name;
    private String phoneNumber;
    private int merchant;
    private String merchantName;
    private int price;
    private int clientCount;
    private String detail;
    private int years;
    private String gender;
}
