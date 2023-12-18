package com.smilegate.digerpostservice.controller.dto.response;

import com.smilegate.digerpostservice.domain.persistence.PostType;

public record PostResponse(
        Long id,
        String title,
        String content,
        PostType postType
) {
}

