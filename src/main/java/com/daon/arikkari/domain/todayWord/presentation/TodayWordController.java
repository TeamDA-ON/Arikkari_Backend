package com.daon.arikkari.domain.todayWord.presentation;

import com.daon.arikkari.domain.todayWord.domain.TodayWord;
import com.daon.arikkari.domain.todayWord.service.GetTodayWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/today")
public class TodayWordController {

    private final GetTodayWordService getTodayWordService;

    @GetMapping
    public ResponseEntity<TodayWord> getTodayWord() {
        return getTodayWordService.execute();
    }

    @PutMapping
    public ResponseEntity<String> updateTodayWord() {
        return getTodayWordService.updateTodayWord();
    }
}
