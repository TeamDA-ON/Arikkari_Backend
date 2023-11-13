package com.daon.arikkari.domain.dictionary.service;

import com.daon.arikkari.domain.dictionary.presentation.dto.response.InfoResponse;
import com.daon.arikkari.domain.question.multiplechoicequestion.domain.MultipleChoiceQuestion;
import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import com.daon.arikkari.domain.question.shortanswerquestion.domain.ShortAnswerQuestion;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    public ResponseEntity<InfoResponse> execute() {
        Random random = new Random();
        long randomIndex = random.nextInt(93);
        if (randomIndex % 2 == 0) {
           MultipleChoiceQuestion multipleChoiceQuestion = multipleChoiceQuestionRepository.findById(randomIndex).get();
           return ResponseEntity.ok(InfoResponse.builder()
                           .description(multipleChoiceQuestion.getDefine())
                           .word(multipleChoiceQuestion.getAnswer() == 1 ?
                                   multipleChoiceQuestion.getSelection1() : multipleChoiceQuestion.getAnswer() == 2 ?
                                   multipleChoiceQuestion.getSelection2() : multipleChoiceQuestion.getSelection3())
                   .description(multipleChoiceQuestion.getDescription())
                   .build());
        }
        else {
            ShortAnswerQuestion shortAnswerQuestion = shortAnswerQuestionRepository.findById(randomIndex).get();
            return ResponseEntity.ok(InfoResponse.builder()
                            .word(shortAnswerQuestion.getAnswer())
                            .description(shortAnswerQuestion.getDefine())
                    .build());
        }

    }
}
