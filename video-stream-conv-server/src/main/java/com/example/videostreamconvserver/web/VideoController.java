package com.example.videostreamconvserver.web;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVideo() {
        return JSONObject.quote("Video");
    }

}
