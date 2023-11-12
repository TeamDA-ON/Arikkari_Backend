package com.daon.arikkari.domain.todayWord.service;

import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import com.daon.arikkari.domain.todayWord.domain.TodayWord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTodayWordService {

    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    private TodayWord todayWord;

    public ResponseEntity<TodayWord> execute() {
        return ResponseEntity.ok(todayWord);
    }
}
