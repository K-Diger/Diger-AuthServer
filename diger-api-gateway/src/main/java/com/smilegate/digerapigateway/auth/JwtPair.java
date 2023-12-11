package com.smilegate.digerapigateway.auth;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
