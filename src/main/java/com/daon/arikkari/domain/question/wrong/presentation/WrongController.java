package com.daon.arikkari.domain.question.wrong.presentation;

import com.daon.arikkari.domain.question.correct.presentation.dto.request.SaveQuestionRequest;
import com.daon.arikkari.domain.question.correct.presentation.dto.response.QuestionResponse;
import com.daon.arikkari.domain.question.wrong.service.AddWrongService;
import com.daon.arikkari.domain.question.wrong.service.GetWrongListService;
import com.daon.arikkari.domain.question.wrong.service.GetWrongService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wrong")
public class WrongController {

    private final GetWrongService getWrongService;
    private final GetWrongListService getWrongListService;
    private final AddWrongService addWrongService;

    @GetMapping
    public ResponseEntity<Long> getCount(HttpServletRequest request) {
        return getWrongService.execute(request);
    }

    @GetMapping("/list")
    public ResponseEntity<List<QuestionResponse>> getWrongList(HttpServletRequest request) {
        return getWrongListService.execute(request);
    }

    @PostMapping
    public ResponseEntity<String> addCount(@RequestBody SaveQuestionRequest request, HttpServletRequest httpServletRequest) {
        return addWrongService.execute(request, httpServletRequest);
    }
}
