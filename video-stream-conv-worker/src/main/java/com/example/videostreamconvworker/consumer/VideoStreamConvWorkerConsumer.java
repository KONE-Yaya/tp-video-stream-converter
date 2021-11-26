package com.example.videostreamconvworker.consumer;

import com.example.videostreamconvworker.config.MessagingConfig;
import com.example.videostreamconvworker.dto.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VideoStreamConvWorkerConsumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumerListener(CustomMessage message) {
        System.out.println(message);
    }

}