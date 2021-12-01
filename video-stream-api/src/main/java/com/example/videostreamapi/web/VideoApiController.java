package com.example.videostreamapi.web;

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
@RequestMapping("/api")
public class VideoApiController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.server.core.address}")
    private String address;

    @RequestMapping( method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String convertVideo(@RequestParam String id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "http://" + address + "/api/status/" + id;
        return JSONObject.valueToString(restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody());

    }
}
