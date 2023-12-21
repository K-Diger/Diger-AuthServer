package com.smilegate.digeruserservice.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdatePointRequest(
        @NotNull(message = "유저는 비어있을 수 없습니다.")
        Long targetUserId,

        @NotNull(message = "증감시킬 포인트는 비어있을 수 없습니다.")
        Integer point
) {

}
