package com.daon.arikkari.global.auth.dto.response;

import lombok.Getter;

@Getter
public class GoogleTokenResponse {
    private String access_token;
    private String token_type;
    private int expires_in;
}
