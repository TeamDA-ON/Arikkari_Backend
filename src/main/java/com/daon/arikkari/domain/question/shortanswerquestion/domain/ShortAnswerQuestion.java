package com.daon.arikkari.domain.question.shortanswerquestion.domain;

import com.daon.arikkari.domain.question.shortanswerquestion.presentation.dto.response.SAQResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ShortAnswerQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private String answer;

    @Column
    private String problem1;

    @Column
    private String problem2;

    @Column(nullable = false)
    private String description;

    @Column
    private String define;

    @Builder
    public ShortAnswerQuestion(String difficulty, String answer, String problem1, String problem2, String description, String define) {
        this.difficulty = difficulty;
        this.answer = answer;
        this.problem1 = problem1;
        this.problem2 = problem2;
        this.description = description;
        this.define = define;
    }

    public SAQResponse toResponse() {
        return SAQResponse.builder()
                .id(this.id)
                .difficulty(this.difficulty)
                .answer(this.answer)
                .commentary(this.description)
                .problem1(this.problem1)
                .problem2(this.problem2)
                .build();
    }

    public ShortAnswerQuestion setDefine(String define) {
        this.define = define;
        return this;
    }
}
