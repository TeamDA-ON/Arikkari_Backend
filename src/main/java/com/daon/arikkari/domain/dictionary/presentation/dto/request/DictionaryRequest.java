package com.daon.arikkari.domain.dictionary.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DictionaryRequest {
    private String key;
    private String q;
}
