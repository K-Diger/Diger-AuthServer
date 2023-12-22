package com.smilegate.digerpostservice.feign.user.dto.response;

public record UserResponse(
        Long id,
        String loginId,
        String nickname,
        Integer point,
        String role
) {
}