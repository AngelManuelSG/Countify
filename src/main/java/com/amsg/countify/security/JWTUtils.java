package com.amsg.countify.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;


/**
 * Utility class created for JWT generation and validation
 *
 * @author: AMSG
 * @version 1.0
 */
@Component
public class JWTUtils {

    @Value("${countify.jwt.secret}")
    private String jwtSecret;

    @Value("${countify.jwt.expiration-ms}")
    private int jwtExpirationMs;

    /**
     * Private method for building the SigningKey we will use for generating jwt
     * @return Key
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Public method for generating a basic jwt for a specific user
     * @param username String with the username
     * @return the jwt
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiracyDate = new Date((new Date()).getTime() + jwtExpirationMs);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiracyDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Public method for validating the JWT signature and expiration
     * @param token String with the JWT
     * @return boolean true if valid, false if invalid or expired
     */
    public boolean validateToken(String token) {
        try {
            parseAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Se captura ExpiredJwtException, MalformedJwtException, SignatureException, etc.
            return false;
        }
    }

    /**
     * Public method to extract the username (subject) from the JWT
     * @param token String with the JWT
     * @return username String
     */
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generic helper method to extract a specific claim from the token
     * @param token String with the JWT
     * @param claimsResolver Function to resolve claim
     * @return T claim value
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Private helper to parse and verify the JWT signature using the 0.12+ API
     * @param token String with the JWT
     * @return Claims payload
     */
    private Claims parseAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
