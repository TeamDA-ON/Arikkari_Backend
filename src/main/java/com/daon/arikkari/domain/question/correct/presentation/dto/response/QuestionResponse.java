package com.daon.arikkari.domain.question.correct.presentation.dto.response;

import com.daon.arikkari.domain.question.correct.domain.QuestionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponse {
    private String answer;
    private String description;
    private QuestionType questionType;
}
