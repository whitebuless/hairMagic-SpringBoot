package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.entity.Share;
import com.flash.hairmagic01.service.ShareService;
import com.flash.hairmagic01.common.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/14 18:48
 */
@CrossOrigin
@RestController
@RequestMapping("/share")
public class ShareController {
    @Autowired
    ShareService shareService;
    @GetMapping("/get")
    public Result findShare(String title){return Result.success(shareService.findShare(title));}
    @PostMapping("/upload")
    public Result uploadShare(@RequestBody Share share){
        try{
            shareService.uploadShare(share);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException)
                return Result.error("插入数据库错误");
            else
                return Result.error("系统错误");
        }
        return Result.success("添加成功");
    }
    @GetMapping("/getbyshopid")
    public Result findShareByShopId(int id){
        return Result.success(shareService.findShareByShopId(id));
    }
}
