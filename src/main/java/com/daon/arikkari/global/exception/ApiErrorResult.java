package com.daon.arikkari.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrorResult {
    private Integer status;
    private String summary;
    private String message;
}
