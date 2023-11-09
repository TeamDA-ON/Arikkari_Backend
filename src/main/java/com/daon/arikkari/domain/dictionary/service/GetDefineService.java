package com.daon.arikkari.domain.dictionary.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "GetDefineService",
        url = "http://opendict.korean.go.kr/api/search"
)
public interface GetDefineService {
    @GetMapping
    String getDictionaryInfo(@RequestParam String key, @RequestParam String q, @RequestParam String req_type);
}
