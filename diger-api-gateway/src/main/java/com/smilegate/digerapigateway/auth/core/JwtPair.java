package com.smilegate.digerapigateway.auth.core;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
