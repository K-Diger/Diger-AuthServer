package com.smilegate.digeruserservice.auth.jwt;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
