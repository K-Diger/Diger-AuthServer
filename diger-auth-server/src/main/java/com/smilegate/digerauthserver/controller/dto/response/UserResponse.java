package com.smilegate.digerauthserver.controller.dto.response;

public record UserResponse(
        Long id,
        String loginId,
        String nickname,
        String role
) {
}

