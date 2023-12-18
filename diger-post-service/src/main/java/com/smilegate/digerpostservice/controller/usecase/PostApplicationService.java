package com.smilegate.digerpostservice.controller.usecase;

import com.smilegate.digerpostservice.controller.dto.response.PostResponse;
import com.smilegate.digerpostservice.domain.PostVo;
import com.smilegate.digerpostservice.domain.persistence.PostType;
import com.smilegate.digerpostservice.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostApplicationService {

    private final PostService postService;

    @Transactional
    public PostResponse createPost(
            String title,
            String content
    ) {
        PostVo postVo = postService.create(title, content, PostType.NORMAL);
        return new PostResponse(
                postVo.getId(),
                postVo.getTitle(),
                postVo.getContent(),
                PostType.NORMAL
        );
    }

    @Transactional
    public PostResponse createNotice(
            String title,
            String content
    ) {
        PostVo postVo = postService.create(title, content, PostType.NOTICE);
        return new PostResponse(
                postVo.getId(),
                postVo.getTitle(),
                postVo.getContent(),
                PostType.NOTICE
        );
    }
}
