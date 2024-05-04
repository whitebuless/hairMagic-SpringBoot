package com.flash.hairmagic01.service;

import com.flash.hairmagic01.entity.HairType;
import com.flash.hairmagic01.mapper.HairTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/22 17:16
 */
@Service
public class HairTypeService {
    @Autowired
    HairTypeMapper hairTypeMapper;

    public HairType[] getAllHairType(){
        HairType[] dbHairType=hairTypeMapper.getAllHairType();
        return dbHairType;
    }

}
