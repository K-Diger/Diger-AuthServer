package com.smilegate.digerpostservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    @GetMapping
    public String test() {
        return "test post Service";
    }
}
