package com.flash.hairmagic01.controller;

import cn.hutool.json.JSONObject;
import com.flash.hairmagic01.entity.User;
import com.flash.hairmagic01.repository.UserRepository;
import com.flash.hairmagic01.utils.set.FollowersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/5/3 22:55
 */
@CrossOrigin
@RestController
public class FollowController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private RedisTemplate<String,User> redisTemplate;

    @Autowired
    public FollowController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<FollowersResponse> getFollowers(@PathVariable int userId) {
        User user = redisTemplate.opsForValue().get(String.valueOf(userId));
        if (user != null) {
            Set<Integer> followers = user.getFollowers();
            FollowersResponse response = new FollowersResponse(followers);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<FollowersResponse> getFollowing(@PathVariable int userId) {
        User user = redisTemplate.opsForValue().get(String.valueOf(userId));
        if (user != null) {
            Set<Integer> following = user.getFollowing();
            FollowersResponse response = new FollowersResponse(following);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/follow")
    public ResponseEntity<String> followUser(@RequestBody String requestBody) {
        try {
            // 解析 JSON 请求体
            JSONObject json = new JSONObject(requestBody);
            int userId = json.getInt("userId");
            int followingId = json.getInt("followingId");

            // 调用 UserRepository 中的 follow 方法
            userRepository.follow(userId, followingId);

            // 返回成功状态码和消息
            return ResponseEntity.ok("关注成功");
        } catch (Exception e) {
            // 返回失败状态码和消息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("关注失败：" + e.getMessage());
        }
    }

    //    取消关注
    @DeleteMapping("/{userId}/unfollowing/{followingId}")
    public String unfollowUser(@PathVariable int userId, @PathVariable int followingId) {
        userRepository.unfollow(userId, followingId);
        return "取消关注" + followingId + "成功   ";
    }
}