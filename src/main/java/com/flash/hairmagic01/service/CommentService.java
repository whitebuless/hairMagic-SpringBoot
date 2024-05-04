package com.flash.hairmagic01.service;

import com.flash.hairmagic01.entity.Comment;
import com.flash.hairmagic01.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/25 14:15
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    public Comment[] getCommentByShareId(int id){
        Comment[] dbComment=commentMapper.getCommentByShareId(id);
        return dbComment;
    }
    public void commentNow(Comment comment){
        commentMapper.commentNow(comment);
    }
}
