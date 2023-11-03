package com.daon.arikkari.domain.question.correct.service;

import com.daon.arikkari.domain.question.correct.domain.Correct;
import com.daon.arikkari.domain.question.correct.domain.QuestionType;
import com.daon.arikkari.domain.question.correct.presentation.dto.request.SaveQuestionRequest;
import com.daon.arikkari.domain.question.correct.repository.CorrectRepository;
import com.daon.arikkari.domain.question.wrong.domain.Wrong;
import com.daon.arikkari.domain.question.wrong.repository.WrongRepository;
import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.repository.UserRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddCorrectService {

    private final CorrectRepository correctRepository;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public ResponseEntity<String> execute(SaveQuestionRequest request, HttpServletRequest httpServletRequest) {
        String email = jwtProvider.extractEmailWithAccessToken(httpServletRequest.getHeader("Authorization").split(" ")[1].trim());
        request.getList().stream().map((id) -> correctRepository.save(Correct
                .builder()
                .email(email)
                .questionId(id)
                .questionType(request.getQuestionType().equals("MCQ") ? QuestionType.MCQ : QuestionType.SAQ)
                .build())).collect(Collectors.toList());
        User user = userRepository.findByEmail(email).get();
        user.addCount((long) request.getList().size());
        return ResponseEntity.ok("success");
    }
}
