package com.smilegate.digeruserservice.controller.dto.request;

public record JoinRequest(
        String loginId,
        String password,
        String nickname
) {
}
