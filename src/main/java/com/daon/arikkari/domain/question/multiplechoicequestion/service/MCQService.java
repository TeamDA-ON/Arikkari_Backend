package com.daon.arikkari.domain.question.multiplechoicequestion.service;

import com.daon.arikkari.domain.question.multiplechoicequestion.domain.MultipleChoiceQuestion;
import com.daon.arikkari.domain.question.multiplechoicequestion.presentation.dto.response.MCQResponse;
import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
            Optional<MultipleChoiceQuestion> question = repository.findById(randomNum);
            if (question.isPresent()) {
                response.add(question.get().toResponse());
            }
            else {
                i--;
            }
        }
        return response;
    }
}
