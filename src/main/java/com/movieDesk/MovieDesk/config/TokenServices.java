package com.movieDesk.MovieDesk.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieDesk.MovieDesk.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenServices {

    @Value("${MovieDesk.security.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("API_MovieDesk")
                .withSubject(user.getEmail())
                .withClaim("name", user.getName())
                .withClaim("id", user.getId())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .sign(algorithm);
    }

    public Optional<JwtUserData> verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT verify = JWT.require(algorithm)
                    .withIssuer("API_MovieDesk")
                    .build()
                    .verify(token);

            return Optional.of(JwtUserData
                    .builder()
                    .id(verify.getClaim("id").asLong())
                    .name(verify.getClaim("name").asString())
                    .email(verify.getSubject())
                    .build());

        }catch (JWTVerificationException e){
            return Optional.empty();
        }
    }



}