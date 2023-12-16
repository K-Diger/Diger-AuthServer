package com.smilegate.digeruserservice.common.security.filter.authentication;

public record AuthenticationRequestForm(
        String loginId,
        String password
) {
}
