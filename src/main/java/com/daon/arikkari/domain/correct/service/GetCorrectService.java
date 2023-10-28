package com.daon.arikkari.domain.correct.service;

import com.daon.arikkari.domain.correct.domain.Correct;
import com.daon.arikkari.domain.correct.repository.CorrectRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCorrectService {

    private final CorrectRepository correctRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<Long> execute(HttpServletRequest request) {
        String email = jwtProvider.extractEmailWithAccessToken(request.getHeader("Authorization").split(" ")[1].trim());
        List<Correct> correctList = correctRepository.findAllByEmail(email);
        if (correctList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
        }
        return ResponseEntity.ok((long) correctList.size());
    }
}
