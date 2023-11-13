package com.daon.arikkari.domain.todayWord.presentation;

import com.daon.arikkari.domain.todayWord.domain.TodayWord;
import com.daon.arikkari.domain.todayWord.service.GetTodayWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/today")
public class TodayWordController {

    private final GetTodayWordService getTodayWordService;

    @GetMapping
    public ResponseEntity<TodayWord> getTodayWord() {
        return getTodayWordService.execute();
    }
}
