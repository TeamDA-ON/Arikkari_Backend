package com.daon.arikkari.domain.user.presentation;

import com.daon.arikkari.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google")
    public ResponseEntity<?> signUp(@RequestParam(name = "code") String code) {
        return authService.execute(code);
    }
}
