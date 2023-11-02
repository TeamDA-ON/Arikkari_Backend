package com.daon.arikkari.domain.question.shortanswerquestion.service;

import com.daon.arikkari.domain.question.shortanswerquestion.presentation.dto.response.SAQResponse;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            response.add(repository.findById(randomNum).orElseThrow().toResponse());
        }
        return response;
    }
}
