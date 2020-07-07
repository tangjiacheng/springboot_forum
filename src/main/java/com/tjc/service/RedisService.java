package com.tjc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: TJC
 * @Date: 2020/6/20 19:25
 * @description: TODO
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void incrPostView(Integer postId) {
        redisTemplate.opsForValue().increment("post:view" + postId);
    }
}
