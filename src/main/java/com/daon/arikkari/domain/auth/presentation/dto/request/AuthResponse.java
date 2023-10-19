package com.daon.arikkari.domain.auth.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
    private String access_token;
    private String refresh_token;
}
