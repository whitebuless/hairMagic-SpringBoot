package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/3/24 9:41
 */
@RestController
public class WebController {
    @RequestMapping
    public Result hello(String name){
        return Result.success(name);
    }
}
