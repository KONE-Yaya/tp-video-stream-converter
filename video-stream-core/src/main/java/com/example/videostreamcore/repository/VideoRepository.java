package com.example.videostreamcore.repository;

import com.example.videostreamcore.dto.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<Video,String> {
}
