package com.rentalcar.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String accessTokenSecret;

    public DecodedJWT decode(String token) {
        Algorithm algorithm = Algorithm.HMAC256(accessTokenSecret);
        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token);
    }

    public String encode(UserDetails user) {

        Algorithm algorithm = Algorithm.HMAC256(accessTokenSecret);

        return JWT
                .create()
                .withSubject(user.getUsername())
//                .withClaim("id", user.getId())
                .withClaim("roles", user
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }
}
