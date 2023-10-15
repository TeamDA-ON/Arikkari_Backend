package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.global.auth.dto.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "GetUserInfoService",
        url = "https://www.googleapis.com/userinfo/v2/me"
)
public interface GetUserInfoService {
    @GetMapping
    UserInfoResponse getUserInfo(@RequestParam String access_token);
}
