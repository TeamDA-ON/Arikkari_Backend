package com.daon.arikkari.domain.auth.service;

import com.daon.arikkari.domain.auth.domain.RefreshToken;
import com.daon.arikkari.domain.auth.presentation.dto.response.NewAccessToken;
import com.daon.arikkari.domain.auth.repository.RefreshTokenRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public ResponseEntity<?> execute(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").split(" ")[1].trim();
        String refreshToken = request.getHeader("Authorization-refresh").split(" ")[1].trim();

        if (refreshTokenRepository.findByAccessToken(accessToken).isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No token on DB");
        }

        String email = jwtProvider.extractEmailwithRefreshToken(refreshToken);
        Optional<RefreshToken> getToken = refreshTokenRepository.findByEmail(email);

        if (getToken.get().getRefreshToken().equals(refreshToken)) {
            String newAccessToken = jwtProvider.createAccessToken(email);
            RefreshToken updateRefreshToken = getToken.get().update(newAccessToken);

            refreshTokenRepository.save(updateRefreshToken);

            return ResponseEntity.ok(NewAccessToken.builder()
                    .accessToken(newAccessToken)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token is incorrect");
    }
}
