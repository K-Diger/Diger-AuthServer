package com.smilegate.digerpostservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    // 400
    E400(
            "E400-1",
            "파라미터가 올바르지 않습니다.",
            HttpStatus.BAD_REQUEST
    ),
    E400_AUTHENTICATE(
            "E400-2",
            "아이디 혹은 비밀번호를 잘 못 입력했습니다.",
            HttpStatus.BAD_REQUEST
    ),

    // 401
    E401(
            "E401-1",
            "해당 요청은 인가가 필요합니다.",
            HttpStatus.UNAUTHORIZED
    ),

    // 403
    E403(
            "E403-1",
            "해당 요청에 대한 권한이 없습니다.",
            HttpStatus.FORBIDDEN
    ),

    // 404
    E404(
            "E404-1",
            "해당 리소스가 존재하지 않습니다.",
            HttpStatus.NOT_FOUND
    ),

    // 405
    E405(
            "E405-1",
            "올바르지 않은 요청 메서드입니다.",
            HttpStatus.METHOD_NOT_ALLOWED
    ),

    // 409
    E409(
            "E409-1",
            "중복된 리소스가 있습니다.",
            HttpStatus.CONFLICT
    ),

    // 500
    E500(
            "E500-1",
            "서버 오류 입니다. 관리자에게 문의해주세요",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    ;

    private final String code;
    private final String message;
    private final HttpStatus status;

}
