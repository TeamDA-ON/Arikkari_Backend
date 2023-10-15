package com.daon.arikkari.global.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtFilterResponse {
    private final Boolean isValid;
    private final String message;

    @Builder
    public JwtFilterResponse(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

}
