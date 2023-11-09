package com.daon.arikkari.domain.dictionary.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefineResponse {
    private String word;
    private String define;
}
