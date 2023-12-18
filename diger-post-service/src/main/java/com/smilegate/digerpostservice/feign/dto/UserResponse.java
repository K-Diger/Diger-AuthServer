package com.smilegate.digerpostservice.feign.dto;

public record UserResponse(
        Long id,
        String loginId,
        String nickname,
        String role
) {
}