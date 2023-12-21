package com.smilegate.digeruserservice.common.jwt.component;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
