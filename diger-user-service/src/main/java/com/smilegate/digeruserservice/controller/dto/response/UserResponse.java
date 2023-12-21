package com.smilegate.digeruserservice.controller.dto.response;

public record UserResponse(
        Long id,
        String loginId,
        String nickname,
        Integer point,
        String role
) {
}

