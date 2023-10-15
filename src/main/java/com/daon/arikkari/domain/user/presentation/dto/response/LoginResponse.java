package com.daon.arikkari.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String access_token;
    private String refresh_token;
}
