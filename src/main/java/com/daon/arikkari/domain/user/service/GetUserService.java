package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.domain.question.correct.repository.CorrectRepository;
import com.daon.arikkari.domain.question.wrong.repository.WrongRepository;
import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.presentation.dto.request.UserRequest;
import com.daon.arikkari.domain.user.presentation.dto.response.UserResponse;
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
public class GetUserService {

    private final UserRepository userRepository;
    private final CorrectRepository correctRepository;
    private final WrongRepository wrongRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<?> execute(HttpServletRequest request) {
        String email = jwtProvider.extractEmailWithAccessToken(request.getHeader("Authorization").split(" ")[1].trim());
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user");
        }

        User user = optionalUser.get();

        return ResponseEntity.status(HttpStatus.OK).body(UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .belong(user.getBelong())
                .correctCount(correctRepository.countByEmail(email))
                .wrongCount(wrongRepository.countByEmail(email))
                .build());
    }
}
