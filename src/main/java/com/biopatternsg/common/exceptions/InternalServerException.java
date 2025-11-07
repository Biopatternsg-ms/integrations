package com.biopatternsg.common.exceptions;

public class InternalServerException extends ExceptionService {
    private static final String MESSAGE = "Oops, something unexpected happens";

    public InternalServerException() {
        super(HttpCode.INTERNAL_SERVER_ERROR, ApiResponseError.builder()
                .message(MESSAGE)
                .build());
    }
}
