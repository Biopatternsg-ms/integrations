package com.biopatternsg.infrastructure.exceptions;

import com.biopatternsg.common.exceptions.ApiResponseError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ExceptionGlobalHandler implements ExceptionMapper<Throwable> {

    private static final String MESSAGE = "Oops, something unexpected happens";

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ApiResponseError.builder()
                        .message(MESSAGE)
                        .build())
                .build();
    }
}
