package com.daon.arikkari.global.auth.dto.request;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GoogleTokenRequest {
    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String grant_type;
}
