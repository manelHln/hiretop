package com.backend.hiretop.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    private static final String JWT_SECRET = "A1B2C3D4E5F6A7B8A1B2C3D4E5F6A7B8A1B2C3D4E5F6A7B8A1B2C3D4E5F6A7B8";

    public String extractUsername(String jwtToken){
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSingnInKey())
                .compact();
    }

    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parser()
                .verifyWith((SecretKey)getSingnInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private Key getSingnInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
