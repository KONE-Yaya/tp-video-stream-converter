package com.example.videostreamconvworker.consumer;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import com.example.videostreamconvworker.config.MessagingConfig;
import com.example.videostreamconvworker.dto.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.encode.enums.X264_PROFILE;
import ws.schild.jave.info.VideoSize;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class VideoStreamConvWorkerConsumer {

    Logger logger = LoggerFactory.getLogger(VideoStreamConvWorkerConsumer.class);

    @Autowired
    RestTemplate restTemplate;


    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumerListener(CustomMessage message) {

        //before conv
        Map<String, String> body = new HashMap<>();
        body.put("id",message.getId());
        body.put("status","In Progress");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Map> entity = new HttpEntity<>(body, headers);

        String url = "http://localhost:8080/api/update/status/";

        logger.info(JSONObject.valueToString(restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody()));

        //conv
        convert(message.getFilename(), message.getMode());

        //after conv
        body.put("status","Completed");

        logger.info(JSONObject.valueToString(restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody()));
    }

    public void convert(String filename, String mode){


       String[] fileandformat = filename.split("\\.");
       String justfilename = fileandformat[0];

        File source = new File("/etc/data/videos/"+ filename);
        File target = new File("/etc/data/videos/" + justfilename + "_converted." + mode);

        /* Step 2. Set Audio Attrributes for conversion*/
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("aac");
        // here 64kbit/s is 64000
        audio.setBitRate(64000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);

        /* Step 3. Set Video Attributes for conversion*/
        VideoAttributes video = new VideoAttributes();
        video.setCodec("h264");
        video.setX264Profile(X264_PROFILE.BASELINE);
        // Here 160 kbps video is 160000
        video.setBitRate(160000);
        // More the frames more quality and size, but keep it low based on devices like mobile
        video.setFrameRate(15);
        video.setSize(new VideoSize(400, 300));

        /* Step 4. Set Encoding Attributes*/
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat(mode);
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);

        /* Step 5. Do the Encoding*/
        try {
            Encoder encoder = new ws.schild.jave.Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);
        } catch (Exception e) {
            /*Handle here the video failure*/
            e.printStackTrace();
        }
    }

}