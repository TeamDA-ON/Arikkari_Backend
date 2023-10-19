package com.daon.arikkari.domain.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {
    @Id
    private String email;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @Builder
    public RefreshToken(String email, String accessToken, String refreshToken) {
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String newAccessToken) {
        this.accessToken = newAccessToken;
        return this;
    }
}
