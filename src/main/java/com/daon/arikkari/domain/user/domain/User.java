package com.daon.arikkari.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String belong;

    @Column(nullable = false)
    private Long correctCount;

    @Column(nullable = false)
    private Long wrongCount;

    @Builder
    public User(String email, String name, String belong, Long correctCount, Long wrongCount) {
        this.email = email;
        this.name = name;
        this.belong = belong;
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
    }
}
