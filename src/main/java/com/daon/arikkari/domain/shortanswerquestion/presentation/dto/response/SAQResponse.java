package com.daon.arikkari.domain.shortanswerquestion.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SAQResponse {
    private Long id;
    private String difficulty;
    private String answer;
    private String problem1;
    private String problem2;
    private String commentary;

    @Builder
    public SAQResponse(Long id, String difficulty, String answer, String problem1, String problem2, String commentary) {
        this.id = id;
        this.difficulty = difficulty;
        this.answer = answer;
        this.problem1 = problem1;
        this.problem2 = problem2;
        this.commentary = commentary;
    }
}
