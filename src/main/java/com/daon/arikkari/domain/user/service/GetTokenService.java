package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.global.auth.dto.request.GoogleTokenRequest;
import com.daon.arikkari.global.auth.dto.response.GoogleTokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "GetTokenService",
        url = "https://oauth2.googleapis.com/token"
)
public interface GetTokenService {
    @PostMapping
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseEntity<GoogleTokenResponse> getAccessToken(@RequestBody GoogleTokenRequest reqeust);
}
