package com.kbrom.charity_lens_backend.auth.service;

import com.kbrom.charity_lens_backend.auth.exception.TokenGenerationException;
import com.kbrom.charity_lens_backend.auth.exception.TokenValidationException;
import com.kbrom.charity_lens_backend.auth.security.CustomUserDetails;
import com.kbrom.charity_lens_backend.common.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Service
public class JwtService{
    private final JwtProperties jwtProperties;
    private final SecretKey signingKey;
    public JwtService(JwtProperties jwtProperties){
        this.jwtProperties=jwtProperties;
        this.signingKey=hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getAuthJwtSecret()));
    }
    public String generateToken(CustomUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",userDetails.getId());
        claims.put("roles",userDetails.getAuthorities());
        return createToken(claims,userDetails.getUsername());
    }
    public String createToken(Map<String,Object> claims,String username){
        try {
            return Jwts.builder()
                    .claims(claims)
                    .subject(username)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + jwtProperties.getAuthJwtValiditySeconds()))
                    .signWith(signingKey)
                    .compact();
        }
        catch(JwtException | IllegalArgumentException e) {
            throw new TokenGenerationException("Token Generation Failed",e);
        }
    }
    public String createToken(String username){
        return createToken(Map.of(),username);
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
    public Long getUserIdFromToken(String token) {
        return getClaimFromToken(token,claims->claims.getId()!=null?Long.parseLong(claims.getId()):null);
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