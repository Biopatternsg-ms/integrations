package com.biopatternsg.infrastructure.exceptions;

import com.biopatternsg.common.exceptions.ApiResponseError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        var apiResponseError = ApiResponseError.builder()
                .message("bad request!")
                .details(new HashMap<>())
                .build();

        exception.getConstraintViolations().forEach(cv -> apiResponseError.addDetail(getFieldName(cv), cv.getMessage()));

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(apiResponseError)
                .build();
    }

    private String getFieldName(ConstraintViolation<?> violation) {
        String[] path = violation.getPropertyPath().toString().split("\\.");
        return path.length > 0 ? path[path.length - 1] : "unknown";
    }
}
