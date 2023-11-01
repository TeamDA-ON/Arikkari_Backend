package com.daon.arikkari.domain.question.correct.presentation;

import com.daon.arikkari.domain.question.correct.presentation.dto.request.SaveQuestionRequest;
import com.daon.arikkari.domain.question.correct.presentation.dto.response.QuestionResponse;
import com.daon.arikkari.domain.question.correct.service.AddCorrectService;
import com.daon.arikkari.domain.question.correct.service.GetCorrectListService;
import com.daon.arikkari.domain.question.correct.service.GetCorrectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/correct")
public class CorrectController {

    private final GetCorrectService getCorrectService;
    private final GetCorrectListService getCorrectListService;
    private final AddCorrectService addCorrectService;

    @GetMapping
    public ResponseEntity<Long> getCount(HttpServletRequest request) {
        return getCorrectService.execute(request);
    }

    @GetMapping("/list")
    public ResponseEntity<List<QuestionResponse>> getCorrectList(HttpServletRequest request) {
        return getCorrectListService.execute(request);
    }

    @PostMapping
    public ResponseEntity<String> addCount(SaveQuestionRequest request, HttpServletRequest httpServletRequest) {
        return addCorrectService.execute(request, httpServletRequest);
    }

}
