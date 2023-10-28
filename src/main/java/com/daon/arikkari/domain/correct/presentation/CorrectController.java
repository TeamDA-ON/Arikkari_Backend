package com.daon.arikkari.domain.correct.presentation;

import com.daon.arikkari.domain.correct.service.GetCorrectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/correct")
public class CorrectController {

    private final GetCorrectService getCorrectService;

    @GetMapping
    public ResponseEntity<Long> getCount(HttpServletRequest request) {
        return getCorrectService.execute(request);
    }

}
