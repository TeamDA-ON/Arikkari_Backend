package com.daon.arikkari.domain.correct.service;

import com.daon.arikkari.domain.correct.domain.Correct;
import com.daon.arikkari.domain.correct.domain.QuestionType;
import com.daon.arikkari.domain.correct.presentation.dto.request.SaveQuestionRequest;
import com.daon.arikkari.domain.correct.repository.CorrectRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCorrectService {

    private final CorrectRepository correctRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<String> execute(SaveQuestionRequest request, HttpServletRequest httpServletRequest) {
        String email = jwtProvider.extractEmailWithAccessToken(httpServletRequest.getHeader("Authorization").split(" ")[1].trim());
        request.getList().stream().map((id) -> correctRepository.save(Correct
                .builder()
                .email(email)
                .questionId(id)
                .questionType(request.getQuestionType().equals("MCQ") ? QuestionType.MCQ : QuestionType.SAQ)
                .build()));
        return ResponseEntity.ok("success");
    }
}
