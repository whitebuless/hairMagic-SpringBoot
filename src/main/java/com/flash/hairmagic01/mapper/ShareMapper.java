package com.flash.hairmagic01.mapper;

import com.flash.hairmagic01.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Array;

/**
 * 功能：share mapper
 * 作者：whitebuless
 * 日期：2024/4/24
 */
@Mapper
public interface ShareMapper {
    @Select("select * from `share` where title like CONCAT('%', #{title}, '%') or search_info like CONCAT('%', #{title}, '%') ORDER BY likes,looked")
    Share[] findShare(String title);

    @Insert("insert into `share`(`hair_type`,`user_id`,`user_name`,`description`,`shop_name`,`shop_id`,`location`,`title`,`imgs`,`search_info`,`type`,`gender`,`create_time`) " +
            "VALUES (#{hairType},#{userId},#{userName},#{description},#{shopName},#{shopId},#{location},#{title},#{imgs},#{searchInfo},#{type},#{gender},NOW())")
    void uploadShare(Share share);

    @Select("select * from `share` where shop_id=#{shopId}")
    Share[] findShareByShopId(int id);
}
