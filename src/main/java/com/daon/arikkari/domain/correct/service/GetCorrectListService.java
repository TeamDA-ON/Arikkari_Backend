package com.daon.arikkari.domain.correct.service;

import com.daon.arikkari.domain.correct.domain.Correct;
import com.daon.arikkari.domain.correct.domain.QuestionType;
import com.daon.arikkari.domain.correct.presentation.dto.response.CorrectResponse;
import com.daon.arikkari.domain.correct.repository.CorrectRepository;
import com.daon.arikkari.domain.multiplechoicequestion.domain.MultipleChoiceQuestion;
import com.daon.arikkari.domain.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import com.daon.arikkari.domain.shortanswerquestion.domain.ShortAnswerQuestion;
import com.daon.arikkari.domain.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCorrectListService {

    private final CorrectRepository correctRepository;
    private final JwtProvider jwtProvider;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;

    public ResponseEntity<List<CorrectResponse>> execute(HttpServletRequest request) {
        String email = jwtProvider.extractEmailWithAccessToken(request.getHeader("Authorization").split(" ")[1].trim());
        List<CorrectResponse> returnList = new ArrayList<>();
        List<Correct> correctList = correctRepository.findAllByEmail(email);
        correctList.stream().map((data) -> {
            if (data.getQuestionType() == QuestionType.MCQ) {
                MultipleChoiceQuestion question = multipleChoiceQuestionRepository.findById(data.getQuestionId()).get();
                returnList.add(CorrectResponse.builder()
                        .questionType(QuestionType.MCQ)
                        .answer(question.getAnswer() == 1 ?
                                question.getSelection1() : question.getAnswer() == 2 ?
                                question.getSelection2() : question.getSelection3())
                        .description(question.getDescription())
                        .build());
            } else {
                ShortAnswerQuestion question = shortAnswerQuestionRepository.findById(data.getQuestionId()).get();
                returnList.add(CorrectResponse.builder()
                                .questionType(QuestionType.SAQ)
                                .answer(question.getAnswer())
                                .description(question.getDescription())
                        .build());
            }
            return null;
        });
        return ResponseEntity.ok(returnList);
    }
}
