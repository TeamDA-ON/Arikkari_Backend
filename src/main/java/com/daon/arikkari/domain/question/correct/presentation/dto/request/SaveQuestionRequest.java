package com.daon.arikkari.domain.question.correct.presentation.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class SaveQuestionRequest {
    private List<Long> list;
    private String questionType;
}
