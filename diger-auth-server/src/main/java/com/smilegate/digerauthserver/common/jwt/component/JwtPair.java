package com.smilegate.digerauthserver.common.jwt.component;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
