package com.smilegate.digeruserservice.controller.dto.response;

import com.smilegate.digeruserservice.common.jwt.component.JwtPair;

public record UserTokenResponse(
        JwtPair jwtPair
) {
}
