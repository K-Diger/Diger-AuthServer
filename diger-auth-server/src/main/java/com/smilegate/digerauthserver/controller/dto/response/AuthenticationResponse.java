package com.smilegate.digerauthserver.controller.dto.response;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
