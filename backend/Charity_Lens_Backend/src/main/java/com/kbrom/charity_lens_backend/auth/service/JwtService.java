package com.kbrom.charity_lens_backend.auth.service;

import com.kbrom.charity_lens_backend.auth.exception.TokenGenerationException;
import com.kbrom.charity_lens_backend.auth.exception.TokenValidationException;
import com.kbrom.charity_lens_backend.common.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Service
public class JwtService{
    private final JwtProperties jwtProperties;
    private final SecretKey signingKey;
    public JwtService(JwtProperties jwtProperties){
        this.jwtProperties=jwtProperties;
        this.signingKey=hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }
    public String generateToken(Map<String,Object> claims,String username){
        try {
            return Jwts.builder()
                    .claims(claims)
                    .subject(username)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                    .signWith(signingKey)
                    .compact();
        }
        catch(JwtException | IllegalArgumentException e) {
            throw new TokenGenerationException("Token Generation Failed",e);
        }
    }
    public String generateToken(String username){
        return generateToken(Map.of(),username);
    }
    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        catch (JwtException e) {
            throw new TokenValidationException("Invalid Token",e);
        }
    }
    private <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
        final Claims claims=getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }
    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }
    public final boolean isTokenExpired(String token) {
        return getExpirationFromToken(token).before(new Date());
    }
    public final boolean isTokenValid(String token, UserDetails userDetails) {
        return getSubjectFromToken(token)!=null&&isTokenExpired(token)&&getSubjectFromToken(token).equals(userDetails.getUsername());
    }


}