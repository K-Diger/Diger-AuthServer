package com.smilegate.digerauthserver.controller.dto.response;

import com.smilegate.digerauthserver.common.jwt.component.JwtPair;

public record UserTokenResponse(
        JwtPair jwtPair
) {
}
