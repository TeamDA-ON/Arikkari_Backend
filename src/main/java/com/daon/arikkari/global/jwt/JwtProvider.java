package com.daon.arikkari.global.jwt;

import com.daon.arikkari.domain.user.domain.User;
import com.daon.arikkari.domain.user.repository.UserRepository;
import com.daon.arikkari.global.jwt.dto.JwtFilterResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider implements InitializingBean {

    @Value("${jwt.secretKey}")
    private String secretKey;
    private Key key;

    private final UserRepository userRepository;
    private String AUTHORIZATION_KEY = "arikkarikey";

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String email) {
        return createToken(email, JwtProperties.ACCESS_TOKEN_EXPIRED);
    }

    public String createRefreshToken(String email) {
        return createToken(email, JwtProperties.REFRESH_TOKEN_EXPIRED);
    }

    private String createToken(String email, Long validation) {
        Date now = new Date();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user"));

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .claim(AUTHORIZATION_KEY, user.getAuthority())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now.getTime() + validation))
                .compact();
    }

    public JwtFilterResponse isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return isValidReturn(true, "토큰이 정상적입니다.");
        } catch (SignatureException e) {
            return isValidReturn(false, "토큰의 시그니쳐가 올바르지 않습니다.");
        } catch (MalformedJwtException e) {
            return isValidReturn(false, "Jwt의 형식이 유효하지 않습니다.");
        } catch (ExpiredJwtException e) {
            return isValidReturn(false, "Jwt가 만료되었습니다.");
        } catch (UnsupportedJwtException e) {
            return isValidReturn(false, "지원되지 않는 Jwt입니다.");
        } catch (IllegalArgumentException | NullPointerException e) {
            return isValidReturn(false, "토큰의 자료형이 잘못되었거나 존재하지 않습니다.");
        }
    }

    public JwtFilterResponse isValidReturn(boolean valid, String message) {
        return JwtFilterResponse.builder()
                .isValid(valid)
                .message(message)
                .build();
    }

    public String extractEmailWithAccessToken(String email) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(email)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthenticaion(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORIZATION_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();
        return new UsernamePasswordAuthenticationToken(jwt, "", authorities);
    }

    public String extractEmailWithRefreshToken(String refreshToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getSubject();
    }
}
