package com.daon.arikkari.domain.multiplechoicequestion.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MCQResponse {
    private Long id;
    private String difficulty;
    private String problem;
    private String selection1;
    private String selection2;
    private String selection3;
    private Long answer;
    private String commentary;

    @Builder
    public MCQResponse(Long id, String difficulty, String problem, String selection1, String selection2, String selection3, Long answer, String commentary) {
        this.id = id;
        this.difficulty = difficulty;
        this.problem = problem;
        this.selection1 = selection1;
        this.selection2 = selection2;
        this.selection3 = selection3;
        this.answer = answer;
        this.commentary = commentary;
    }
}
