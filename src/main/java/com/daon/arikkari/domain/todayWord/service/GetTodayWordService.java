package com.daon.arikkari.domain.todayWord.service;

import com.daon.arikkari.domain.question.multiplechoicequestion.domain.MultipleChoiceQuestion;
import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import com.daon.arikkari.domain.question.shortanswerquestion.domain.ShortAnswerQuestion;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import com.daon.arikkari.domain.todayWord.domain.TodayWord;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GetTodayWordService {

    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    private TodayWord todayWord;

    public ResponseEntity<TodayWord> execute() {
        return ResponseEntity.ok(todayWord);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateTodayWord() {
        Random random = new Random();
        Long randomNum = random.nextLong(113);
        if (randomNum % 2 == 0) {
            MultipleChoiceQuestion question = multipleChoiceQuestionRepository.findById(randomNum).orElseThrow(() -> new RuntimeException("RuntimeException"));
            todayWord = TodayWord.builder()
                    .answer(question.getAnswer() == 1 ?
                            question.getSelection1() : question.getAnswer() == 2 ?
                            question.getSelection2() : question.getSelection3())
                    .define(question.getDefine())
                    .description(question.getDescription())
                    .build();
        } else {
            ShortAnswerQuestion question = shortAnswerQuestionRepository.findById(randomNum).orElseThrow(() -> new RuntimeException("RuntimeException"));
            todayWord = TodayWord.builder()
                    .answer(question.getAnswer())
                    .define(question.getDefine())
                    .description(question.getDescription())
                    .build();
        }
    }

    @PostConstruct
    public void initTodayWord() {
        updateTodayWord();
    }
}
