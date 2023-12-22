package com.smilegate.digerpostservice.controller.usecase;

import com.smilegate.digerpostservice.controller.dto.response.PostResponse;
import com.smilegate.digerpostservice.domain.PostVo;
import com.smilegate.digerpostservice.domain.persistence.PostType;
import com.smilegate.digerpostservice.domain.service.PostService;
import com.smilegate.digerpostservice.feign.user.UserFeign;
import com.smilegate.digerpostservice.feign.user.dto.request.UserUpdatePointRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostApplicationService {

    private final PostService postService;
    private final UserFeign userFeign;

    @Transactional
    public PostResponse createPost(
            String token,
            String title,
            String content
    ) {
        PostVo postVo = postService.create(title, content, PostType.NORMAL);

        UserUpdatePointRequest updatePointRequest = new UserUpdatePointRequest(
                userFeign.loadMyInfo(token).id(),
                10
        );

        userFeign.updateUserPoint(
                token,
                updatePointRequest
        );

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
