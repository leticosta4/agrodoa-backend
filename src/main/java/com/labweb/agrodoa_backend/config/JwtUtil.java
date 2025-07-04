package com.labweb.agrodoa_backend.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {  //gera e valida os toens de segurança

    @Value("${jwt.secret}")
    private String chaveSecreta;

    @Value("${jwt.expiration}")
    private long tempoExpiracao;

    public String geraToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao)) // 1h
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraiEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean tokenValido(String token, UserDetails userDetails) {
        final String email = extraiEmail(token);
        return (email.equals(userDetails.getUsername())) && !tokenExpirado(token); //esse getUsername seria o equivalente a getEmail

        //talvez seja melhor fazer um customUserDetails
    }

    private boolean tokenExpirado(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) { //extrai info codificada do token JWT
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() { //converte a chave secreta em um obj Key para assinar o token
        return Keys.hmacShaKeyFor(chaveSecreta.getBytes(StandardCharsets.UTF_8));
    }
}
