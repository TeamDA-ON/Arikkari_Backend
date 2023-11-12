package com.daon.arikkari.domain.todayWord.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodayWord {
    private String answer;
    private String define;
    private String description;
}
