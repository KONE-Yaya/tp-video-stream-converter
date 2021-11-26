package com.example.videostreamcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude={MongoAutoConfiguration.class})
@EnableCaching
@EnableMongoRepositories("com.example.videostreamcore.repository")
public class VideoStreamCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoStreamCoreApplication.class, args);
	}

}
