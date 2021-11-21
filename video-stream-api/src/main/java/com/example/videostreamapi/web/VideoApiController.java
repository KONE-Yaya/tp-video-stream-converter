package com.example.videostreamapi.web;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class VideoApiController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVideoApi() {
        return JSONObject.quote("Video-API");
    }
}
