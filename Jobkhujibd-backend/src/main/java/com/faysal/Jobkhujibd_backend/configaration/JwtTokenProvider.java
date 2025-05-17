package com.faysal.Jobkhujibd_backend.configaration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    // Create a secret key for signing the JWT token
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${app.jwt.expiration}")
    private int jwtExpirationMs;

    // Generate JWT token from Authentication object
    public String createToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        // Adding custom claims (user ID, email, role)
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("id", userPrincipal.getId())
                .claim("email", userPrincipal.getEmail())
                .claim("role", userPrincipal.getRole().name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    // Extract username from JWT token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature: {}", token);
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token: {}", token);
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token: {}", token);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token: {}", token);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty: {}", token);
        }
        return false;
    }
}
