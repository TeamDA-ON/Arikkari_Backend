package com.daon.arikkari.domain.question.wrong.service;

import com.daon.arikkari.domain.question.correct.domain.QuestionType;
import com.daon.arikkari.domain.question.correct.presentation.dto.request.SaveQuestionRequest;
import com.daon.arikkari.domain.question.wrong.domain.Wrong;
import com.daon.arikkari.domain.question.wrong.repository.WrongRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddWrongService {
    private final WrongRepository wrongRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public ResponseEntity<String> execute(SaveQuestionRequest request, HttpServletRequest httpServletRequest) {
        String email = jwtProvider.extractEmailWithAccessToken(httpServletRequest.getHeader("Authorization").split(" ")[1].trim());
        request.getList().stream().map((id) -> wrongRepository.save(Wrong
                .builder()
                .email(email)
                .questionId(id)
                .questionType(request.getQuestionType().equals("MCQ") ? QuestionType.MCQ : QuestionType.SAQ)
                .build())).collect(Collectors.toList());
        return ResponseEntity.ok("success");
    }
}
