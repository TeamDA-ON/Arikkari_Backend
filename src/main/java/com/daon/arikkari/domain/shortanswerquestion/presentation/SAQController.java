package com.daon.arikkari.domain.shortanswerquestion.presentation;

import com.daon.mvp.domain.shortanswerquestion.presentation.dto.response.SAQResponse;
import com.daon.mvp.domain.shortanswerquestion.service.SAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saq")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SAQController {

    private final SAQService service;

    @GetMapping("/get")
    public ResponseEntity<List<SAQResponse>> getQuestion() {
        return ResponseEntity.ok(service.execute());
    }
}
