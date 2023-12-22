package com.smilegate.digerpostservice.common.jwt.component;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
