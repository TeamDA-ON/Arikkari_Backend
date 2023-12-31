package com.daon.arikkari.domain.question.multiplechoicequestion.domain;

import com.daon.arikkari.domain.question.multiplechoicequestion.presentation.dto.response.MCQResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MultipleChoiceQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private String problem;

    @Column(nullable = false)
    private String selection1;

    @Column(nullable = false)
    private String selection2;

    @Column(nullable = false)
    private String selection3;

    @Column(nullable = false)
    private Long answer;

    @Column(nullable = false)
    private String description;

    @Column
    private String define;

    @Builder
    public MultipleChoiceQuestion(String difficulty, String problem, String selection1, String selection2, String selection3, Long answer, String description, String define) {
        this.difficulty = difficulty;
        this.problem = problem;
        this.selection1 = selection1;
        this.selection2 = selection2;
        this.selection3 = selection3;
        this.answer = answer;
        this.description = description;
        this.define = define;
    }

    public MCQResponse toResponse() {
        return MCQResponse.builder()
                .id(this.id)
                .difficulty(this.difficulty)
                .problem(this.problem)
                .selection1(this.selection1)
                .selection2(this.selection2)
                .selection3(this.selection3)
                .answer(this.answer)
                .commentary(this.description)
                .build();
    }

    public MultipleChoiceQuestion setDefine(String define) {
        this.define = define;
        return this;
    }
}
