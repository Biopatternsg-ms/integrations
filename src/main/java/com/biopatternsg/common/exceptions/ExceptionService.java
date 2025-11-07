package com.biopatternsg.common.exceptions;

import lombok.Getter;

@Getter
public class ExceptionService extends RuntimeException {
    private final transient HttpCode httpCode;
    private final transient ApiResponseError apiResponseError;

    public ExceptionService(HttpCode httpCode, ApiResponseError apiResponseError) {
        this.httpCode = httpCode;
        this.apiResponseError = apiResponseError;
    }
}
