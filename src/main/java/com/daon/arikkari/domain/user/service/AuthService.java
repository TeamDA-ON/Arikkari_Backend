package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.presentation.dto.response.LoginResponse;
import com.daon.arikkari.domain.user.repository.UserRepository;
import com.daon.arikkari.global.auth.dto.request.GoogleTokenRequest;
import com.daon.arikkari.global.auth.dto.response.GoogleTokenResponse;
import com.daon.arikkari.global.auth.dto.response.UserInfoResponse;
import com.daon.arikkari.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GoogleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GoogleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String RedirectUri;

    private final GetTokenService getTokenService;
    private final GetUserInfoService getUserInfoService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<LoginResponse> execute(String code) {
        GoogleTokenResponse googleTokenResponse = getTokenService.getAccessToken(
                GoogleTokenRequest
                        .builder()
                        .code(code)
                        .client_id(GoogleClientId)
                        .client_secret(GoogleClientSecret)
                        .redirect_uri(RedirectUri)
                        .grant_type("authorization_code")
                        .build()
        );
        UserInfoResponse userInfoResponse = getUserInfoService.getUserInfo(googleTokenResponse.getAccess_token());
        if (userRepository.findByEmail(userInfoResponse.getEmail()).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(userInfoResponse.getEmail())
                            .name(userInfoResponse.getName())
                            .correctCount(0L)
                            .wrongCount(0L)
                            .build()
            );
        }

        String accessToken = jwtProvider.createAccessToken(userInfoResponse.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(userInfoResponse.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.builder()
                .access_token(accessToken)
                .refresh_token(refreshToken)
                .build());
    }
}
