package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/set/{key}/{value}")
    public void set(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? value.toString() : "Key not found";
    }
}
