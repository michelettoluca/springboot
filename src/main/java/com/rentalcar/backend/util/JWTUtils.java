package com.rentalcar.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.rentalcar.backend.dto.response.UserBaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String accessTokenSecret;

    public DecodedJWT decode(String token) {
        Algorithm algorithm = Algorithm.HMAC256(accessTokenSecret);
        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token);
    }

    public String encode(UserBaseResponse user) {

        Algorithm algorithm = Algorithm.HMAC256(accessTokenSecret);

        return JWT
                .create()
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("roles", Collections.singletonList(user.getRole().name()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }
}
