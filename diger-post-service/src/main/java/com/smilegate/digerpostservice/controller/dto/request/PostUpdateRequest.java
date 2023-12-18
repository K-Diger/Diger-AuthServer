package com.smilegate.digerpostservice.controller.dto.request;

public record PostUpdateRequest(
        String title,
        String content
) {
}
