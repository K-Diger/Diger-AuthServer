package com.smilegate.digerauthserver.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.smilegate.digerauthserver.common.exception.ExceptionType.E400;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            TypeMismatchException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<ExceptionResponse> handleRequestValidationException(Exception e) {
        ExceptionType exception = E400;
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exception(exception.name())
                .code(exception.getCode())
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getStatus().getReasonPhrase())
                .build();

        log.error("code : {}, message : {}", exceptionResponse.getCode(), exceptionResponse.getMessage());

        return new ResponseEntity<>(exceptionResponse, exception.getStatus());
    }

    @ExceptionHandler(value = {UserServerException.class})
    public ResponseEntity<ExceptionResponse> handleBaseException(UserServerException e) {
        String className = e.getClass().getName();
        ExceptionType exceptionType = e.getExceptionType();
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exception(className.substring(className.lastIndexOf(".") + 1))
                .code(exceptionType.getCode())
                .message(exceptionType.getMessage())
                .status(exceptionType.getStatus().value())
                .error(exceptionType.getStatus().getReasonPhrase())
                .build();

        log.error("code : {}, message : {}", exceptionResponse.getCode(), exceptionResponse.getMessage());

        return new ResponseEntity<>(exceptionResponse, exceptionType.getStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        HttpStatus status = INTERNAL_SERVER_ERROR;
        String code = "NO_CATCH_ERROR";
        String className = e.getClass().getName();
        String message = e.getMessage();

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exception(className.substring(className.lastIndexOf(".") + 1))
                .code(code)
                .message(message)
                .status(status.value())
                .error(status.getReasonPhrase())
                .build();

        log.error("code : {}, message : {}", exceptionResponse.getCode(), exceptionResponse.getMessage());

        return new ResponseEntity<>(exceptionResponse, status);
    }
}
