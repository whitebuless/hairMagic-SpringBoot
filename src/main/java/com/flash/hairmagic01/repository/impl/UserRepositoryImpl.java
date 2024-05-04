package com.flash.hairmagic01.repository.impl;

import com.flash.hairmagic01.entity.User;
import com.flash.hairmagic01.mapper.UserMapper;
import com.flash.hairmagic01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/5/3 22:49
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final RedisTemplate<String, User> redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void follow(int userId, int followingId) {
        User user = redisTemplate.opsForValue().get(String.valueOf(userId));
        if (user == null) {
            // 如果用户不存在，则创建用户并设置默认属性
            User userNow = userMapper.findUserById(userId);
            userNow.setFollowing(new HashSet<>()); // 初始化 following
            userNow.setFollowers(new HashSet<>()); // 初始化 followers
//            user.setId(userId);
//            user.setUsername("New User"); // 设置默认用户名
            // 设置其他默认属性...
            redisTemplate.opsForValue().set(String.valueOf(userId), userNow);
        }
        // 从 Redis 中获取关注目标用户对象
        User followingUser = redisTemplate.opsForValue().get(String.valueOf(followingId));
        if (followingUser == null) {
            // 如果关注目标用户不存在，则创建用户并设置默认属性
            User followingUserNow =  userMapper.findUserById(followingId);
            followingUserNow.setFollowing(new HashSet<>()); // 初始化 following
            followingUserNow.setFollowers(new HashSet<>()); // 初始化 followers
//            followingUser.setId(followingId);
//            followingUser.setUsername("New User"); // 设置默认用户名
            // 设置其他默认属性...
            redisTemplate.opsForValue().set(String.valueOf(followingId), followingUserNow);
        }
        user = redisTemplate.opsForValue().get(String.valueOf(userId));
        followingUser = redisTemplate.opsForValue().get(String.valueOf(followingId));
        user.getFollowing().add(followingId);
        followingUser.getFollowers().add(userId);
        redisTemplate.opsForValue().set(String.valueOf(userId), user);
        redisTemplate.opsForValue().set(String.valueOf(followingId), followingUser);
    }
    @Override
    public void unfollow(int userId, int followingId) {
        User user = redisTemplate.opsForValue().get(String.valueOf(userId));
        User followingUser = redisTemplate.opsForValue().get(String.valueOf(followingId));
        if (user != null && followingUser != null) {
            user.getFollowing().remove(followingId);
            followingUser.getFollowers().remove(userId);
            redisTemplate.opsForValue().set(String.valueOf(userId), user);
            redisTemplate.opsForValue().set(String.valueOf(followingId), followingUser);
        }
    }

    @Override
    public Set<Integer> getFollowing(int userId) {
        User user = redisTemplate.opsForValue().get(String.valueOf(userId));
        if (user != null) {
            return user.getFollowing();
        }
        return null;
    }

    @Override
    public Set<Integer> getFollowers(int userId) {
        User user = redisTemplate.opsForValue().get(String.valueOf(userId));
        if (user != null) {
            return user.getFollowers();
        } else {
            return Collections.emptySet(); // 返回一个空的集合
        }
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}