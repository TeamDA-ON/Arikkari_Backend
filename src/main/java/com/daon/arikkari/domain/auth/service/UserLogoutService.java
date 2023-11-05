package com.daon.arikkari.domain.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLogoutService {
    public ResponseEntity<String> execute() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("success");
    }
}
