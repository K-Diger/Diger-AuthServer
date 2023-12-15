package com.smilegate.digeruserservice.dto.request;

public record JoinRequest(
        String loginId,
        String password,
        String nickname
) {
}
