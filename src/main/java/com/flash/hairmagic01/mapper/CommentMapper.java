package com.flash.hairmagic01.mapper;

import com.flash.hairmagic01.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM `comment` where share_id=#{id}")
    Comment[] getCommentByShareId (int id);
    @Insert("INSERT INTO `comment` (`user_id`, `user_name`, `comment`, `likes`, `share_id`, `answers`, `answer_for_id`, `create_time`) " +
            "VALUES (#{userId}, #{userName}, #{comment}, 0, #{shareId}, 0, 0, NOW());")
    void commentNow(Comment comment);
}