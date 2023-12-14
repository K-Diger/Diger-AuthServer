package com.smilegate.digeruserservice.controller.dto.response;

import com.smilegate.digeruserservice.auth.jwt.JwtPair;

public record UserTokenResponse(
        JwtPair jwtPair
) {
}
