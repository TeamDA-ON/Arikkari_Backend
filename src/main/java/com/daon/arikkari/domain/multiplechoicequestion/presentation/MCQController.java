package com.daon.arikkari.domain.multiplechoicequestion.presentation;

import com.daon.arikkari.domain.multiplechoicequestion.presentation.dto.response.MCQResponse;
import com.daon.arikkari.domain.multiplechoicequestion.service.MCQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mcq")
public class MCQController {

    private final MCQService mcqService;

    @GetMapping("/get")
    public ResponseEntity<List<MCQResponse>> getQuestion() {
        return ResponseEntity.ok(mcqService.execute());
    }

}
