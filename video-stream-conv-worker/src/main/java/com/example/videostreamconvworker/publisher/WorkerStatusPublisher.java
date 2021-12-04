package com.example.videostreamconvworker.publisher;

import com.example.videostreamconvworker.config.MessagingConfig;
import com.example.videostreamconvworker.dto.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class WorkerStatusPublisher {

    @Autowired
    private RabbitTemplate template;

    @RequestMapping(value ="/update/status/", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String publishMessage(@RequestBody Map<String,String> body) {
        CustomMessage message = new CustomMessage();

        message.setId(body.get("id"));
        message.setStatus(body.get("status"));
        template.convertAndSend(MessagingConfig.EXCHANGE,
                MessagingConfig.ROUTING_KEY_STATUS, message);

        return "Video with id: "+ message.getId() + " status has been updated: " + message.getStatus();
    }

}
