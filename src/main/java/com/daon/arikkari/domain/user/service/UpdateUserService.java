package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.presentation.dto.request.UpdateUserRequest;
import com.daon.arikkari.domain.user.repository.UserRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<String> execute(UpdateUserRequest request) {
        String email = jwtProvider.extractEmail(request.getAccessToken());
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user"));
        user.setName(request.getName());
        user.setBelong(request.getBelong());
        userRepository.save(user);
        ResponseEntity.ok("succcess");
    }
}
