package com.smilegate.digeruserservice.common.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CookieAgent {

    private static final int COOKIE_VALIDITY = 60 * 60; // 1hours

    public Cookie createAccessToken(String value) {
        return createCookie("accessToken", value);
    }

    public Cookie createRefreshToken(String value) {
        return createCookie("refreshToken", value);
    }

    private Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(COOKIE_VALIDITY);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .findAny()
                .orElse(null);
    }

}