package com.smilegate.digerpostservice.controller;

import com.smilegate.digerpostservice.controller.dto.request.PostCreateRequest;
import com.smilegate.digerpostservice.controller.dto.response.PostResponse;
import com.smilegate.digerpostservice.controller.usecase.PostApplicationService;
import com.smilegate.digerpostservice.feign.user.UserFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostApplicationService postApplicationService;
    private final UserFeign userFeign;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<PostResponse> create(
            @RequestHeader("Authorization") String token,
            @RequestBody PostCreateRequest postCreateRequest
    ) {

        return ResponseEntity.ok().body(
                postApplicationService.createPost(
                        token,
                        postCreateRequest.title(),
                        postCreateRequest.content()
                )
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/notice")
    public ResponseEntity<PostResponse> createNotice(
            @RequestBody PostCreateRequest postCreateRequest
    ) {

        return ResponseEntity.ok().body(
                postApplicationService.createNotice(
                        postCreateRequest.title(),
                        postCreateRequest.content()
                )
        );
    }
}
