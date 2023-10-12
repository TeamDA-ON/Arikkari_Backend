package com.daon.arikkari.global.jwt;

import com.daon.arikkari.global.auth.AuthDetails;
import com.daon.arikkari.global.auth.CustomOAuth2UserService;
import com.daon.arikkari.global.jwt.dto.JwtFilterResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider implements InitializingBean {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private final CustomOAuth2UserService customOAuth2UserService;
    private Key key;

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
        Claims claims = Jwts.claims();
        claims.put("email", email);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.ES256)
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

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = (AuthDetails) customOAuth2UserService.loadUserByUsername(extractEmail(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    private String extractEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }
}
