package com.example.videostreamconvserver.web;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.server.core.address}")
    private String address;

    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String convertVideo(@RequestParam Map<String, String> body ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Map> entity = new HttpEntity<>(body, headers);

        String url = "http://" + address + "/api/conv";

        return JSONObject.valueToString(restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody());

    }

}
