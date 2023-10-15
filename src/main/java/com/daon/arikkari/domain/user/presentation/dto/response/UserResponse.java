package com.daon.arikkari.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String email;
    private String name;
    private String belong;
    private Long correctCount;
    private Long wrongCount;

}
