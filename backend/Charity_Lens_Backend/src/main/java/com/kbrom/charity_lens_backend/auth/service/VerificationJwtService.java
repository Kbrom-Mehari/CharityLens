package com.kbrom.charity_lens_backend.auth.service;

import com.kbrom.charity_lens_backend.common.config.JwtProperties;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
@RequiredArgsConstructor
@Service
public class VerificationJwtService {
    private final JwtProperties jwtProperties;
    private final SecretKey signingKey=hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getEmailJwtSecret()));

    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(jwtProperties.getEmailJwtValiditySeconds())))
                .signWith(signingKey)
                .compact();
    }
    public boolean isTokenValid(String token){
        try{
            Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        catch(JwtException | IllegalArgumentException e){
            return false;
        }

    }
    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }
}
