package com.flash.hairmagic01.utils;



import cn.hutool.core.util.IdUtil;

import java.util.UUID;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/29 11:09
 */
public class CodeGeneratorUtil {
    /**
     * 生成指定长度的验证码
     *
     * @param length 长度
     * @return
     */
    public static String generateCode(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
    /**
     * 雪花算法生成用户注册的id
     */
    public static long snowflake() {
        return IdUtil.getSnowflakeNextId();
    }
}