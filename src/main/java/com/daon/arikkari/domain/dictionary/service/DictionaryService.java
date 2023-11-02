package com.daon.arikkari.domain.dictionary.service;

import com.daon.arikkari.domain.dictionary.presentation.dto.request.GetInfoRequest;
import lombok.RequiredArgsConstructor;
import org.json.XML;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final GetDefineService getDefineService;


    public ResponseEntity<String> execute(GetInfoRequest request) {
        return ResponseEntity.ok(XML.toJSONObject(
                getDefineService.getDictionaryInfo("64A45DFBC7ECA509F1B4763A17152D8E", request.getWord()))
                .getJSONObject("channel").getJSONObject("item").getJSONObject("sense").getString("definition"));
    }
}
