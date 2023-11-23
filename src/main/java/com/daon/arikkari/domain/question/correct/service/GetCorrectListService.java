package com.daon.arikkari.domain.question.correct.service;

import com.daon.arikkari.domain.question.correct.domain.Correct;
import com.daon.arikkari.domain.question.correct.domain.QuestionType;
import com.daon.arikkari.domain.question.correct.presentation.dto.response.QuestionResponse;
import com.daon.arikkari.domain.question.correct.repository.CorrectRepository;
import com.daon.arikkari.domain.question.multiplechoicequestion.domain.MultipleChoiceQuestion;
import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import com.daon.arikkari.domain.question.shortanswerquestion.domain.ShortAnswerQuestion;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCorrectListService {

    private final CorrectRepository correctRepository;
    private final JwtProvider jwtProvider;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;

    public ResponseEntity<List<QuestionResponse>> execute(HttpServletRequest request) {
        String email = jwtProvider.extractEmailWithAccessToken(request.getHeader("Authorization").split(" ")[1].trim());
        List<Correct> correctList = correctRepository.findAllByEmail(email);
        return ResponseEntity.ok(correctList.stream().map(data -> {
            if (data.getQuestionType() == QuestionType.MCQ) {
                Optional<MultipleChoiceQuestion> question = multipleChoiceQuestionRepository.findById(data.getQuestionId());
                if (question.isPresent()) {
                    MultipleChoiceQuestion multipleChoiceQuestion = question.get();
                    return QuestionResponse.builder()
                            .questionType(QuestionType.MCQ)
                            .answer(multipleChoiceQuestion.getAnswer() == 1 ?
                                    multipleChoiceQuestion.getSelection1() : multipleChoiceQuestion.getAnswer() == 2 ?
                                    multipleChoiceQuestion.getSelection2() : multipleChoiceQuestion.getSelection3())
                            .description(multipleChoiceQuestion.getDescription())
                            .define(multipleChoiceQuestion.getDefine())
                            .build();
                }
            } else {
                Optional<ShortAnswerQuestion> question = shortAnswerQuestionRepository.findById(data.getQuestionId());
                if (question.isPresent()) {
                    ShortAnswerQuestion shortAnswerQuestion = question.get();
                    return QuestionResponse.builder()
                            .questionType(QuestionType.SAQ)
                            .answer(shortAnswerQuestion.getAnswer())
                            .description(shortAnswerQuestion.getDescription())
                            .define(shortAnswerQuestion.getDefine())
                            .build();
                }
            }
            return QuestionResponse.builder()
                    .questionType(QuestionType.MCQ)
                    .answer("데이터가 없습니다")
                    .description("데이터가 없습니다")
                    .define("데이터가 없습니다")
                    .build();
        }).collect(Collectors.toList()));
    }
}
