package com.smilegate.digerauthserver.common.exception;

import lombok.Getter;

@Getter
public class AuthServerException extends RuntimeException {

    private final ExceptionType exceptionType;

    public AuthServerException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}