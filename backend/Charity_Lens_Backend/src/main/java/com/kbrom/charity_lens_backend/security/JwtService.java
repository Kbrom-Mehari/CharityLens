package com.kbrom.charity_lens_backend.security;

import com.kbrom.charity_lens_backend.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
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
        this.jwtProperties = jwtProperties;
        this.signingKey=hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }
    public final String generateToken(Map<String,Object> extraClaims,String username ){
       return Jwts.builder()
            .claims(extraClaims)
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+jwtProperties.getExpiration()))
            .signWith(signingKey)
            .compact();
    }
    public final String generateToken(String username){
       return generateToken(Map.of(),username);
    }
    private final Claims getClaimsFromToken(String token){
        try{
            return Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        catch (JwtException ex){
            throw new RuntimeException("Invalid token", ex);
        }

    }
    private final <T> T getClaimFromToken(String token, Function<Claims,T > claimsResolver){
        final Claims claims=getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    public final String getSubjectFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    public final Date getExpirationFromToken(String token){
       return getClaimFromToken(token,Claims::getExpiration);
    }
    public final boolean isTokenExpired(String token){
        return getExpirationFromToken(token).before(new Date());
    }
    public final boolean isTokenValid(String token,String username){
        String tokenUsername=getClaimFromToken(token,Claims::getSubject);
        return tokenUsername!=null && !isTokenExpired(token) && tokenUsername.equals(username);
    }

}