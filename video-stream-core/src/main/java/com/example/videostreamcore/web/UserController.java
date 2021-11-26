package com.example.videostreamcore.web;

import com.example.videostreamcore.dto.User;
import com.example.videostreamcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apy")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("users", user.getId(), user);
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
