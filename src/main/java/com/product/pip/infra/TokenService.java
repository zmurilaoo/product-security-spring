package com.product.pip.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.product.pip.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service

public class TokenService {
    //Função das clsses é gerar os tokens..
    @Value("${api.security.token.secret}")
    private String secret;


    //Criar o token
    public String genarateToke(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("auth-api").withSubject(user.getLogin()).withExpiresAt(genExpirationDate()).sign(algorithm);

            return token;

        }catch (JWTCreationException exception){
            throw  new RuntimeException("Error while generating token" + exception.getMessage());

        }

    }


    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }


    private Instant genExpirationDate(){
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("03:00"));
    }

}
