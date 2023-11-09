package com.daon.arikkari.domain.test;

import com.daon.arikkari.domain.dictionary.service.GetDefineService;
import com.daon.arikkari.domain.question.multiplechoicequestion.repository.MultipleChoiceQuestionRepository;
import com.daon.arikkari.domain.question.shortanswerquestion.repository.ShortAnswerQuestionRepository;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class testController {

    private final GetDefineService getDefineService;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    private final JsonParser jsonParser = new JsonParser();

    @PostMapping
    @Transactional
    public ResponseEntity<String> execute() {

            multipleChoiceQuestionRepository.findAll().stream()
                    .map(data -> {
                        JsonObject answer = jsonParser.parse(getDefineService.getDictionaryInfo("6DB284CB9FFDEAC31EDA157A4D04F01E",
                                data.getAnswer() == 1 ? data.getSelection1() : data.getAnswer() == 2 ? data.getSelection2() : data.getSelection3(), "json"))
                                .getAsJsonObject()
                                .getAsJsonObject("channel");
                        if (answer.has("item")) {
                            return multipleChoiceQuestionRepository.save(
                                    data.setDefine(
                                            answer.getAsJsonArray("item").get(0)
                                            .getAsJsonObject()
                                            .getAsJsonArray("sense").get(0)
                                            .getAsJsonObject()
                                            .get("definition").toString()
                                    )
                            );
                        }
                        return "API 결과 없음";
                            }
                    ).collect(Collectors.toList());
            shortAnswerQuestionRepository.findAll().stream()
                    .map(data -> {
                        JsonObject answer = jsonParser.parse(getDefineService.getDictionaryInfo("6DB284CB9FFDEAC31EDA157A4D04F01E", data.getAnswer(), "json"))
                                .getAsJsonObject()
                                .getAsJsonObject("channel");
                        if (answer.has("item")) {
                            return shortAnswerQuestionRepository.save(
                                    data.setDefine(
                                            answer.getAsJsonArray("item").get(0)
                                                    .getAsJsonObject()
                                                    .getAsJsonArray("sense").get(0)
                                                    .getAsJsonObject()
                                                    .get("definition").toString()
                                    )
                            );
                        }
                        return "API 결과 없음";
                    }).collect(Collectors.toList()
                    );

        return ResponseEntity.ok("success");
    }
}
