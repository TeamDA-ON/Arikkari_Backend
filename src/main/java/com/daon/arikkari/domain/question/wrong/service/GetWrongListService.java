package com.daon.arikkari.domain.question.wrong.service;

import com.daon.arikkari.domain.question.correct.domain.Correct;
import com.daon.arikkari.domain.question.correct.domain.QuestionType;
import com.daon.arikkari.domain.question.correct.presentation.dto.response.QuestionResponse;
import com.daon.arikkari.domain.question.multiplechoicequestion.domain.MultipleChoiceQuestion;
import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import com.daon.arikkari.domain.question.shortanswerquestion.domain.ShortAnswerQuestion;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import com.daon.arikkari.domain.question.wrong.domain.Wrong;
import com.daon.arikkari.domain.question.wrong.repository.WrongRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetWrongListService {
    private final WrongRepository wrongRepository;
    private final JwtProvider jwtProvider;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;

    public ResponseEntity<List<QuestionResponse>> execute(HttpServletRequest request) {
        String email = jwtProvider.extractEmailWithAccessToken(request.getHeader("Authorization").split(" ")[1].trim());
        List<QuestionResponse> returnList = new ArrayList<>();
        List<Wrong> correctList = wrongRepository.findAllByEmail(email);
        correctList.stream().map((data) -> {
            if (data.getQuestionType() == QuestionType.MCQ) {
                MultipleChoiceQuestion question = multipleChoiceQuestionRepository.findById(data.getQuestionId()).get();
                returnList.add(QuestionResponse.builder()
                        .questionType(QuestionType.MCQ)
                        .answer(question.getAnswer() == 1 ?
                                question.getSelection1() : question.getAnswer() == 2 ?
                                question.getSelection2() : question.getSelection3())
                        .description(question.getDescription())
                        .build());
            } else {
                ShortAnswerQuestion question = shortAnswerQuestionRepository.findById(data.getQuestionId()).get();
                returnList.add(QuestionResponse.builder()
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
