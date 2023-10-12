package com.daon.arikkari.global.jwt;

import com.daon.arikkari.global.exception.ApiErrorResult;
import com.daon.arikkari.global.jwt.dto.JwtFilterResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String accessToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            accessToken = authorizationHeader.split(" ")[1].trim();
        }

        if (accessToken != null) {
            JwtFilterResponse accessTokenResult = jwtProvider.isValid(accessToken);

            if (accessTokenResult != null && !accessTokenResult.getIsValid()) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                String responseDto = new ObjectMapper().writeValueAsString(
                        ApiErrorResult.builder()
                                .status(HttpStatus.FORBIDDEN.value())
                                .summary("JWTException")
                                .message(accessTokenResult.getMessage())
                                .build()
                );
                response.getWriter().write(responseDto);
                return;
            }

            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
