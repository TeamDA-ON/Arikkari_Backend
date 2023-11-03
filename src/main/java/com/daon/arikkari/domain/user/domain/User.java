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
    private Authority authority;

    @Column(nullable = false)
    private Long correctCount;

    @Builder
    public User(String email, String name, String belong, Authority authority, Long correctCount) {
        this.email = email;
        this.name = name;
        this.belong = belong;
        this.authority = authority;
        this.correctCount = correctCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public void addCount(Long count) {
        this.correctCount += count;
    }

}
