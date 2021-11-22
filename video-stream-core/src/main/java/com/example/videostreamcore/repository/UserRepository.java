package com.example.videostreamcore.repository;

import com.example.videostreamcore.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
