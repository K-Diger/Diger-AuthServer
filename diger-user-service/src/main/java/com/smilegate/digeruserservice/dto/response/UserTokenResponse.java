package com.smilegate.digeruserservice.dto.response;

import com.smilegate.digeruserservice.auth.jwt.JwtPair;

public record UserTokenResponse(
        JwtPair jwtPair
) {
}
