package com.RodriSolution.course.security;


import com.RodriSolution.course.model.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expirationMinutes:30}") // TEMPO DE EXPIRAÇÃO = 30 minutos
    private Long expirationMinutes;

    //----ALGORITMO---//
    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    //----GERAÇÃO DO TOKEN---//
    public String generateToken(User user){
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getUserRole().name())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(generateExpirationDate()))
                .sign(getAlgorithm());

    }

    //----VALIDAÇÃO----//
    public boolean isTokenValid(String token){
        try {
            getDecodedJWT(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public Long getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return Long.parseLong(decodedJWT.getSubject());
    }

    public String getUserRoleFromToken(String token) {
        return getDecodedJWT(token).getClaim("role").asString();
    }

    private DecodedJWT getDecodedJWT(String token) {
        return JWT.require(getAlgorithm()).build().verify(token);
    }

    //----EXPIRAÇÃO---//
    public Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(expirationMinutes).toInstant(ZoneOffset.of("-03:00"));
    }

}
