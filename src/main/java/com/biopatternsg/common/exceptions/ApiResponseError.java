package com.biopatternsg.common.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class ApiResponseError {
    private String message;
    private Map<String,Object> details;

    public void addDetail(String key, Object value){
        this.details.put(key,value);

    }
}
