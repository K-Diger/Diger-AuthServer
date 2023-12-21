package com.smilegate.digerapigateway.filter.authentication.jwt.component;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
