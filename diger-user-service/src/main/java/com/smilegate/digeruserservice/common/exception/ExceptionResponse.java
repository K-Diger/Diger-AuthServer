package com.smilegate.digeruserservice.common.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    private String exception;
    private String code;
    private String message;
    private Integer status;
    private String error;
}