/*
 * Copyright © 2026 biopatternsg (biopatternsg@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
