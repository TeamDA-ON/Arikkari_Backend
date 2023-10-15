package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.global.auth.dto.request.GoogleTokenRequest;
import com.daon.arikkari.global.auth.dto.response.GoogleTokenResponse;
import com.daon.arikkari.global.auth.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    public ResponseEntity<?> execute(String code) {
        ResponseEntity<GoogleTokenResponse> googleTokenResponse = getTokenService.getAccessToken(
                GoogleTokenRequest
                        .builder()
                        .code(code)
                        .client_id(GoogleClientId)
                        .client_secret(GoogleClientSecret)
                        .redirect_uri(RedirectUri)
                        .grant_type("authorization_code")
                        .build()
        );
        System.out.println(googleTokenResponse.getBody().getAccess_token());
        ResponseEntity<UserInfoResponse> userInfoResponse = getUserInfoService.getUserInfo(googleTokenResponse.getBody().getAccess_token());
        UserInfoResponse userInfo = userInfoResponse.getBody();

        System.out.println("ID: " + userInfo.getId());
        System.out.println("Email: " + userInfo.getEmail());
        System.out.println("Verified Email: " + userInfo.getVerified_email());
        System.out.println("Name: " + userInfo.getName());
        System.out.println("Given Name: " + userInfo.getGiven_name());
        System.out.println("Family Name: " + userInfo.getFamily_name());
        System.out.println("Picture: " + userInfo.getPicture());
        System.out.println("Locale: " + userInfo.getLocale());

        return ResponseEntity.ok("success");
    }
}
