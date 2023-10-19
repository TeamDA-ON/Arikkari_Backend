package com.daon.arikkari.domain.auth.presentation;

import com.daon.arikkari.domain.auth.service.AuthService;
import com.daon.arikkari.domain.auth.service.RefreshTokenService;
import com.daon.arikkari.domain.auth.presentation.dto.request.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestParam(name = "code") String code) {
        return authService.execute(code);
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> updateRefreshToken(HttpServletRequest request) {
        return refreshTokenService.execute(request);
    }

}
