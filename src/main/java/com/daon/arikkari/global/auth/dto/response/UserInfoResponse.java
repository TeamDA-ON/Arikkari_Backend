package com.daon.arikkari.global.auth.dto.response;

import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String id;
    private String email;
    private Boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;
}
