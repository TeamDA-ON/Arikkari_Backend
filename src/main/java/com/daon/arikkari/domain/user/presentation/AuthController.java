package com.daon.arikkari.domain.user.presentation;

import com.daon.arikkari.domain.user.presentation.dto.request.UserRequest;
import com.daon.arikkari.domain.user.presentation.dto.response.LoginResponse;
import com.daon.arikkari.domain.user.service.AuthService;
import com.daon.arikkari.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final GetUserService getUserService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signUp(@RequestParam(name = "code") String code) {
        return authService.execute(code);
    }

    @GetMapping("get")
    public ResponseEntity<?> getUser(@RequestBody UserRequest request) {
        return getUserService.execute(request);
    }

}
