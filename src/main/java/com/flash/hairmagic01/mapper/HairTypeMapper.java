package com.flash.hairmagic01.mapper;

import com.flash.hairmagic01.entity.HairType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HairTypeMapper {
    @Select("SELECT * FROM `hairtype`")
    HairType[] getAllHairType();

}
