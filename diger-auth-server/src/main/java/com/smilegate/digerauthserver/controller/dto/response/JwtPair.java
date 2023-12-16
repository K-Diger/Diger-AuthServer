package com.smilegate.digerauthserver.controller.dto.response;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
