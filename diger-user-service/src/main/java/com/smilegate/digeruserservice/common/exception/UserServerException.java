package com.smilegate.digeruserservice.common.exception;

import lombok.Getter;

@Getter
public class UserServerException extends RuntimeException {

    private final ExceptionType exceptionType;

    public UserServerException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}