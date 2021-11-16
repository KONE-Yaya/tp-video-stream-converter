package com.example.videostreamapi.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class VideoApiController {

    @GetMapping
    public String getVideoApi() {
        return "Video-API";
    }
}
