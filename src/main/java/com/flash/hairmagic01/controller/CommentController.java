package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import com.flash.hairmagic01.entity.Comment;
import com.flash.hairmagic01.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/25 14:17
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;


    @GetMapping("/getByShareId")
    public Result getCommentByShareId(int id){
        return Result.success(commentService.getCommentByShareId(id));
    }

    @PostMapping("/subcomment")
    public Result commentNow(@RequestBody Comment comment){
        try{
            commentService.commentNow(comment);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException)
                return Result.error("插入数据库错误");
            else
                return Result.error("系统错误");
        }
        return Result.success();
    }
}
