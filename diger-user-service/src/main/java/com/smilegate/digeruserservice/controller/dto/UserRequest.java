package com.smilegate.digeruserservice.controller.dto;

import lombok.Getter;

public class UserRequest {

    @Getter
    public record UserLoginRequest() {
        private static String loginId;
        private static String password;
    }
}
