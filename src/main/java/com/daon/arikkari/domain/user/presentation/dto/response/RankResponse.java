package com.daon.arikkari.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RankResponse {
    private String name;
    private String belong;
    private Long point;
}
