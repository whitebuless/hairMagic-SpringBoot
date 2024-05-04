package com.flash.hairmagic01.repository;

import com.flash.hairmagic01.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/5/3 22:47
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    void follow(int userId, int followingId); // 用户关注其他用户
    void unfollow(int userId, int followingId); // 用户取消关注其他用户
    Set<Integer> getFollowing(int userId); // 获取用户的关注列表
    Set<Integer> getFollowers(int userId); // 获取关注该用户的用户列表
}