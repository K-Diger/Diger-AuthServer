package com.smilegate.digerauthserver.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "로그인 아이디는 비어있을 수 없습니다.")
        String loginId,

        @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
        String password
) {

}
