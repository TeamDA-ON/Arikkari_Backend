package com.daon.arikkari.domain.dictionary.presentation;

import com.daon.arikkari.domain.dictionary.presentation.dto.request.GetInfoRequest;
import com.daon.arikkari.domain.dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public ResponseEntity<String> getInfo(@RequestBody GetInfoRequest request) {
        return dictionaryService.execute(request);
    }

}
