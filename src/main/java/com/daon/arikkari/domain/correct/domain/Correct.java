package com.daon.arikkari.domain.correct.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Correct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private String questionType;

    @Builder
    public Correct(String email, Long questionId, String questionType) {
        this.email = email;
        this.questionId = questionId;
        this.questionType = questionType;
    }
}
