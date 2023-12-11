package com.smilegate.digeruserservice.common;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class APIResponse<T> {
    private T data;
    private Object exception;
}
