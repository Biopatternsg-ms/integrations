package com.biopatternsg.common.exceptions;

public class UnprocessableEntityException extends ExceptionService {

    public UnprocessableEntityException(ApiResponseError apiResponseError) {
        super(HttpCode.UNPROCESSABLE_ENTITY, apiResponseError);
    }

    public UnprocessableEntityException(String message) {
        super(HttpCode.UNPROCESSABLE_ENTITY, ApiResponseError.builder()
                .message(message)
                .build());
    }
}
