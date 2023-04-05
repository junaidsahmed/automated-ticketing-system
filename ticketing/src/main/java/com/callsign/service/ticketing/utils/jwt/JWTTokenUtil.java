package com.callsign.service.ticketing.utils.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.callsign.service.ticketing.models.jwt.JWTUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Slf4j
@Component
public class JWTTokenUtil implements Serializable {

    @Value("${app.auth.jwtSecret}")
    private String jwtSecret;

    @Value("${app.auth.jwtExpirationMs}")
    private int jwtExpirationMs;

    private static final String TOKEN_PREFIX="Bearer ";

    public String generateJWTToken(Authentication authentication){

        JWTUserDetail userPrincipal = (JWTUserDetail) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withIssuer("junaid")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+jwtExpirationMs))
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
        log.info("JWT token generated against "+ userPrincipal.getUsername());
        return token;
    }
    public boolean validateJwtToken(String jwtToken) {
        try {
            JWT.require(Algorithm.HMAC512(jwtSecret.getBytes()))
                    .build().verify(jwtToken.replace(TOKEN_PREFIX, ""));
            return true;
        } catch (SignatureVerificationException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        }  catch (TokenExpiredException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (AlgorithmMismatchException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
    public String getUserNameFromJwtToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtSecret.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }
}
