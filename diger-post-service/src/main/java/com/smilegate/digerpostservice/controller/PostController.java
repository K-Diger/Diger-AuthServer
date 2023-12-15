package com.smilegate.digerpostservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor
public class PostController {

    @PostMapping
    public String test() {
        return "test post Service";
    }
}
