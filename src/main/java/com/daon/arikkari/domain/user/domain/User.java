package com.daon.arikkari.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WrongAnswer> wrongAnswers = new ArrayList<>();

    @Column(nullable = false)
    private Authority authority;

    @Builder
    public User(String email, String name, String belong, Long correctCount, Authority authority) {
        this.email = email;
        this.name = name;
        this.belong = belong;
        this.correctCount = correctCount;
        this.authority = authority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public void addCount(Long correctCount) {
        this.correctCount += correctCount;
    }

}
