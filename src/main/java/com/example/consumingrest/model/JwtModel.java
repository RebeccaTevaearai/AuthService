package com.example.consumingrest.model;

import com.example.consumingrest.data.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtModel {

    static final String secret = "2jXSDPoSuJmhXLBhhoYZgxHmonoeOOg40zdpnVMkCGI=";
    //Base64.getDecoder().decode(secret)

    static public String newJwt(User user) {

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();

        String jwtToken = Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .setSubject("authToken")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30L, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }

    static public Jws<Claims> parseJWT(String token) {
        String secret = "2jXSDPoSuJmhXLBhhoYZgxHmonoeOOg40zdpnVMkCGI=";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parser()
                .setSigningKey(hmacKey)
                .parseClaimsJws(token);

        return jwt;
    }

}
