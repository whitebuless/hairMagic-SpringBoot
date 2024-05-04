package com.flash.hairmagic01.utils;

import java.util.Random;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/29 13:25
 */
public class RandomStringGenerator {

    public static String generateRandomString(int length) {
        // 定义包含所有可能字母的字符集
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        // 生成指定长度的随机字符串
        for (int i = 0; i < length; i++) {
            // 从字符集中随机选择一个字符并添加到字符串构建器中
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}