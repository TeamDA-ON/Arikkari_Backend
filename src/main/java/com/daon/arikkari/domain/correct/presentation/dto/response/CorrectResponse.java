package com.daon.arikkari.domain.correct.presentation.dto.response;

import com.daon.arikkari.domain.correct.domain.QuestionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CorrectResponse {
    private String answer;
    private String description;
    private QuestionType questionType;
}
