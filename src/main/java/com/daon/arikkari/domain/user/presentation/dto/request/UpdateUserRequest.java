package com.daon.arikkari.domain.user.presentation.dto.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String name;
    private String belong;
    private String accessToken;
}
