package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.presentation.dto.request.UpdateCorrectedRequest;
import com.daon.arikkari.domain.user.repository.UserRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCorrectedService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<String> execute(UpdateCorrectedRequest request, HttpServletRequest httpServletRequest) {
        String email = jwtProvider.extractEmail(httpServletRequest);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().addCount(request.getCorrectCount());
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No User");
    }
}
