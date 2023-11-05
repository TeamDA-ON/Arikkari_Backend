package com.daon.arikkari.domain.auth.presentation;

import com.daon.arikkari.domain.auth.service.UserLoginService;
import com.daon.arikkari.domain.auth.service.RefreshTokenService;
import com.daon.arikkari.domain.auth.presentation.dto.response.AuthResponse;
import com.daon.arikkari.domain.auth.service.UserLogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserLoginService userLoginService;
    private final RefreshTokenService refreshTokenService;
    private final UserLogoutService userLogoutService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestParam(name = "code") String code) {
        return userLoginService.execute(code);
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> updateRefreshToken(HttpServletRequest request) {
        return refreshTokenService.execute(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(HttpServletRequest request) {
        return userLogoutService.execute();
    }

}
