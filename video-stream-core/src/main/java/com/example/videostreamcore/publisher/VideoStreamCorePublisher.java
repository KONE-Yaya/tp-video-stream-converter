package com.example.videostreamcore.publisher;

import com.example.videostreamcore.config.MessagingConfig;
import com.example.videostreamcore.dto.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class VideoStreamCorePublisher {

    @Autowired
    private  RabbitTemplate template;


    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MessagingConfig.EXCHANGE,
                MessagingConfig.ROUTING_KEY, message);

        return "Message Published";
    }
}
