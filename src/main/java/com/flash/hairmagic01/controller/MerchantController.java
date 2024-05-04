package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import com.flash.hairmagic01.entity.Merchant;
import com.flash.hairmagic01.entity.User;
import com.flash.hairmagic01.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/16 19:30
 */
@CrossOrigin
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @GetMapping("/getbyuserid")
    public Result getByUserid(int id){
        return Result.success(merchantService.getMerchantByUserId((id)));
    }

    @PostMapping("/getbyall")
    public Result getByAll(@RequestBody Merchant merchant){
        return Result.success(merchantService.getMerchantByAll((merchant)));
    }

//    新增
    @PostMapping("/add")
    public Result add(@RequestBody Merchant merchant){
        merchantService.addMerchant(merchant);
        return Result.success("新增成功！");
    }
}
