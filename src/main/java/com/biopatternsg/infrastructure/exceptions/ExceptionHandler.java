package com.biopatternsg.infrastructure.exceptions;

import com.biopatternsg.common.exceptions.ExceptionService;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<ExceptionService> {

    @Override
    public Response toResponse(ExceptionService exceptionService) {
        return Response.status(exceptionService.getHttpCode().getCode())
                .entity(exceptionService.getApiResponseError())
                .build();
    }
}
