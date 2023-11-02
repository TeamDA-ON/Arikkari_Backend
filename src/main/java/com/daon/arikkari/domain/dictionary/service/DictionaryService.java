package com.daon.arikkari.domain.dictionary.service;

import com.daon.arikkari.domain.dictionary.presentation.dto.request.DictionaryRequest;
import com.daon.arikkari.domain.dictionary.presentation.dto.request.GetInfoRequest;
import com.daon.arikkari.domain.dictionary.presentation.dto.response.DictionaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final GetDefineService getDefineService;


    public ResponseEntity<String> execute(GetInfoRequest request) {
        return ResponseEntity.ok(getDefineService.getDictionaryInfo(DictionaryRequest.builder()
                .key("64A45DFBC7ECA509F1B4763A17152D8E")
                .q(request.getWord())
                .build()).getDefinition());
    }
}
