package com.daon.arikkari.domain.question.multiplechoicequestion.service;

import com.daon.arikkari.domain.question.multiplechoicequestion.presentation.dto.response.MCQResponse;
import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MCQService {

    private final MultipleChoiceQuestionRepository repository;

    public List<MCQResponse> execute() {
        Random random = new Random();
        Long a = repository.findMaxId();
        List<MCQResponse> response = new ArrayList<MCQResponse>();
        for (int i = 1; i <= 5; i++) {
            Long randomNum = random.nextLong(a) + 1;
            response.add(repository.findById(randomNum).orElseThrow().toResponse());
        }
        return response;
    }
}
