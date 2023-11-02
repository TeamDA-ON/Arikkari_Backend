package com.daon.arikkari.domain.dictionary.service;

import com.daon.arikkari.domain.dictionary.presentation.dto.request.DictionaryRequest;
import com.daon.arikkari.domain.dictionary.presentation.dto.response.DictionaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "GetDefineService",
        url = "https://krdict.korean.go.kr/api/search"
)
public interface GetDefineService {
    @GetMapping
    DictionaryResponse getDictionaryInfo(@RequestBody DictionaryRequest request);
}
