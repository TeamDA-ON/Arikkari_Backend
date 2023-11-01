package com.daon.arikkari.domain.question.wrong.service;

import com.daon.arikkari.domain.question.wrong.domain.Wrong;
import com.daon.arikkari.domain.question.wrong.repository.WrongRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetWrongService {
    private final WrongRepository wrongRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<Long> execute(HttpServletRequest request) {
        String email = jwtProvider.extractEmailWithAccessToken(request.getHeader("Authorization").split(" ")[1].trim());
        List<Wrong> wrongList = wrongRepository.findAllByEmail(email);
        if (wrongList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
        }
        return ResponseEntity.ok((long) wrongList.size());
    }
}
