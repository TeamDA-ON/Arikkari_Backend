package com.daon.arikkari.domain.question.shortanswerquestion.service;

import com.daon.arikkari.domain.question.shortanswerquestion.domain.ShortAnswerQuestion;
import com.daon.arikkari.domain.question.shortanswerquestion.presentation.dto.response.SAQResponse;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SAQService {

    private final ShortAnswerQuestionRepository repository;
    public List<SAQResponse> execute() {
        Long a = repository.findMaxId();
        Random random = new Random();
        List<SAQResponse> response = new ArrayList<>();
        for (long i = 1; i <= 5; i++) {
            Long randomNum = random.nextLong(a) + 1;
            Optional<ShortAnswerQuestion> question = repository.findById(randomNum);
            if (question.isPresent()) {
                response.add(question.get().toResponse());
            } else {
                i--;
            }
        }
        return response;
    }
}
