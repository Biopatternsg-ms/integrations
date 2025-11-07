package com.biopatternsg.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@ToString
@Getter
public enum HttpCode {
    INTERNAL_SERVER_ERROR(500),
    UNPROCESSABLE_ENTITY(422);

    private final Integer code;
}
