package com.daon.arikkari.domain.todayWord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class TodayWord {
    private String answer;
    private String define;
    private String description;
}
