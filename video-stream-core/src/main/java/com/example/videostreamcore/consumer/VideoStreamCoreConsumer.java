package com.example.videostreamcore.consumer;

import com.example.videostreamcore.config.MessagingConfig;
import com.example.videostreamcore.dto.CustomMessage;
import com.example.videostreamcore.dto.Video;
import com.example.videostreamcore.repository.VideoRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class VideoStreamCoreConsumer {

    Logger logger = LoggerFactory.getLogger(VideoStreamCoreConsumer.class);

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = MessagingConfig.QUEUE_STATUS)
    public void consumerListener(CustomMessage message) {
        logger.info("Status updating work from queue...");
        logger.info(JSONObject.valueToString(message));

        HashOperations hashOperations = redisTemplate.opsForHash();
        Video v = (Video) hashOperations.get("videos", message.getId());

        v.setStatus(message.getStatus());
        //update status in cache
        hashOperations.put("videos", v.getId(), v);

        if(message.getStatus() == "Completed"){
            videoRepository.save(v);
            logger.info("*** Updating MongoDB status for video with id: " + v.getId() + " and status: " + v.getStatus() + " ***");
        }

        logger.info("*** End of status updating ***");

    }

}
