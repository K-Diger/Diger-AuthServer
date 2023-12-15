package com.smilegate.digeruserservice.dto.response;

public record UserResponse(
        Long id,
        String loginId,
        String nickname,
        String role
) {
}

