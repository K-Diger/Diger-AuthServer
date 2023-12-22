package com.smilegate.digerpostservice.feign.user.errordecoder;

import com.smilegate.digerpostservice.common.exception.ExceptionType;
import com.smilegate.digerpostservice.common.exception.PostServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class UserFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404:
                return new PostServerException(ExceptionType.E404);
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
