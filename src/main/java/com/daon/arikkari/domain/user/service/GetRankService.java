package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.presentation.dto.response.RankResponse;
import com.daon.arikkari.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetRankService {

    private final UserRepository userRepository;

    public ResponseEntity<List<RankResponse>> execute() {
        return ResponseEntity.ok(userRepository.findAll(Sort.by(Sort.Order.desc("correctCount"))).stream()
                .map(user -> RankResponse.builder()
                        .name(user.getName())
                        .belong(user.getBelong())
                        .point(user.getCorrectCount() * 20)
                        .build())
                .collect(Collectors.toList()));
    }
}
