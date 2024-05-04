package com.flash.hairmagic01.entity;

import lombok.Data;
import java.util.Date;
/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/14 18:36
 */
@Data
public class Share {
    private int id;
    private String hairType;
    private int userId;
    private String userName;
    private String description;
    private String shopName;
    private int shopId;
    private String location;
    private String title;
    private String imgs;
    private String searchInfo;
    private String type;
    private Date createTime;
    private int comments;
    private int likes;
    private int looked;
    private String gender;
}
