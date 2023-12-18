package com.smilegate.digerpostservice.common.exception;

import lombok.Getter;

@Getter
public class PostServerException extends RuntimeException {

    private final ExceptionType exceptionType;

    public PostServerException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}