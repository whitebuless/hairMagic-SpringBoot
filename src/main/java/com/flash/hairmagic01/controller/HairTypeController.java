package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import com.flash.hairmagic01.service.HairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/22 17:19
 */
@CrossOrigin
@RestController
@RequestMapping("/hairtype")
public class HairTypeController {
    @Autowired
    HairTypeService hairTypeService;

    @GetMapping("/alltype")
    public Result getAllHairType(){
        return Result.success(hairTypeService.getAllHairType());
    }
}
