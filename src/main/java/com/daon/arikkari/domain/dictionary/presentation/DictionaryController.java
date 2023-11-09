package com.daon.arikkari.domain.dictionary.presentation;

import com.daon.arikkari.domain.dictionary.presentation.dto.response.DefineResponse;
import com.daon.arikkari.domain.dictionary.presentation.dto.response.InfoResponse;
import com.daon.arikkari.domain.dictionary.service.DefinitionService;
import com.daon.arikkari.domain.dictionary.service.DictionaryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private final DefinitionService definitionService;
    @GetMapping
    public ResponseEntity<InfoResponse> getInfo() {
        return dictionaryService.execute();
    }

    @GetMapping("/define")
    public ResponseEntity<DefineResponse> getDefinition() {
        return definitionService.execute();
    }

}
