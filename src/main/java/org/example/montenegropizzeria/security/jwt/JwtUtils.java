package org.example.montenegropizzeria.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    // Generar token de acceso
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar el token de acceso
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    // Obtener el username del token
    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un solo claim
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todos los claims
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtener la clave de firma del token
    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
