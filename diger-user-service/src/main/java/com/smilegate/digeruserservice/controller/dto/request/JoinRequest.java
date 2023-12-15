package com.smilegate.digeruserservice.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JoinRequest(
        @NotBlank(message = "로그인 아이디는 비어있을 수 없습니다.")
        @Size(min = 6, message = "로그인 아이디는 최소 6자 이상이여야 합니다.")
        String loginId,

        @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
        @Size(min = 12, message = "비밀번호는 최소 12자 이상이여야 합니다.")
        String password,

        @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
        @Size(min = 6, message = "닉네임은 최소 6자 이상이여야 합니다.")
        String nickname
) {
}
