package com.daon.arikkari.domain.user.presentation;

import com.daon.arikkari.domain.user.presentation.dto.request.UpdateUserRequest;
import com.daon.arikkari.domain.user.presentation.dto.request.UserRequest;
import com.daon.arikkari.domain.user.presentation.dto.response.LoginResponse;
import com.daon.arikkari.domain.user.presentation.dto.response.RankResponse;
import com.daon.arikkari.domain.user.service.AuthService;
import com.daon.arikkari.domain.user.service.GetRankService;
import com.daon.arikkari.domain.user.service.GetUserService;
import com.daon.arikkari.domain.user.service.UpdateUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final GetUserService getUserService;
    private final UpdateUserService updateUserService;
    private final GetRankService getRankService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signUp(@RequestParam(name = "code") String code) {
        return authService.execute(code);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestBody UserRequest request) {
        return getUserService.execute(request);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest request) {
        return updateUserService.execute(request);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<RankResponse>> getRanking() {
        return getRankService.execute();
    }

}
