package com.oneday.researcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    @Autowired
    public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateJWT(Authentication auth) {
        Instant now = Instant.now();
        String scope = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(auth.getName())
                .issuer("https://oneday.com")
                .issuedAt(now)
                .claim("roles", scope)
                .claim("username", auth.getName())
                .claim("iat", now.getEpochSecond())
                .claim("exp", now.plusSeconds(60 * 60).getEpochSecond())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public String getUsername(String token) {
        return jwtDecoder.decode(token).getClaimAsString("username");
    }

    public String getRoles(String token) {
        return jwtDecoder.decode(token).getClaimAsString("roles");
    }

    public String getSubject(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Objects.requireNonNull(jwtDecoder.decode(token).getExpiresAt()).isBefore(Instant.now());
    }

    public boolean validateToken(String token) {
        // "Bearer "를 제거하여 토큰 문자열 추출
        token = token.replace("Bearer ", "");
        return !isTokenExpired(token);
    }


}
