package com.example.videostreamcore.publisher;

import com.example.videostreamcore.config.MessagingConfig;
import com.example.videostreamcore.dto.CustomMessage;
import com.example.videostreamcore.dto.Video;
import com.example.videostreamcore.repository.VideoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class VideoStreamCorePublisher {

    @Autowired
    private  RabbitTemplate template;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value ="/conv", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String publishMessage(@RequestBody Map<String,String> body) {
        CustomMessage message = new CustomMessage();
        
        message.setId(UUID.randomUUID().toString());
        message.setFilename(body.get("filename"));
        message.setMode(body.get("mode"));
        message.setConvDate(new Date());
        message.setStatus("received");
        template.convertAndSend(MessagingConfig.EXCHANGE,
                MessagingConfig.ROUTING_KEY, message);

        Video video = new Video();
        video.setId(message.getId());
        video.setConvDate(new Date());
        video.setStatus(message.getStatus());
        video.setFilename(message.getFilename());
        video.setMode(message.getMode());
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("videos", video.getId(), video);
        videoRepository.save(video);

        return "Your video has been uploaded with id:"+ message.getId();
    }

    @RequestMapping(value ="/status/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getVideoStatus(@PathVariable String id) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.get("videos", id);
        Video v = (Video) hashOperations.get("videos", id);
        return "Your video status is: "+ v.getStatus();
    }
}
