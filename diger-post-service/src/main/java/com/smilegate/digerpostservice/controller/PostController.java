package com.smilegate.digerpostservice.controller;

import com.smilegate.digerpostservice.controller.dto.request.PostCreateRequest;
import com.smilegate.digerpostservice.controller.dto.response.PostResponse;
import com.smilegate.digerpostservice.controller.usecase.PostApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostApplicationService postApplicationService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<PostResponse> create(
            @RequestBody PostCreateRequest postCreateRequest
    ) {

        return ResponseEntity.ok().body(
                postApplicationService.createPost(
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
