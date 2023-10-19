package com.daon.arikkari.domain.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NewAccessToken {
    private String accessToken;
    @Builder
    public NewAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
