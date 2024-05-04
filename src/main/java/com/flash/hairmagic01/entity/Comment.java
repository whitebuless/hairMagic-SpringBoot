package com.flash.hairmagic01.entity;

import lombok.Data;

import java.util.Date;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/25 14:09
 */
@Data
public class Comment {
    private int id;
    private int userId;
    private String userName;
    private String comment;
    private int likes;
    private int shareId;
    private int answers;
    private int answerForId;
    private Date createTime;
}
