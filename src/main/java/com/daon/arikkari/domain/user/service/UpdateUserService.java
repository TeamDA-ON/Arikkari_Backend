package com.daon.arikkari.domain.user.service;

import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.presentation.dto.request.UpdateUserRequest;
import com.daon.arikkari.domain.user.repository.UserRepository;
import com.daon.arikkari.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<String> execute(UpdateUserRequest request, HttpServletRequest httpServletRequest) {
        String email = jwtProvider.extractEmailWithAccessToken(httpServletRequest.getHeader("Authorization").split(" ")[1].trim());
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user"));
        user.setName(request.getName());
        user.setBelong(request.getBelong());
        userRepository.save(user);
        return ResponseEntity.ok("succcess");
    }
}
