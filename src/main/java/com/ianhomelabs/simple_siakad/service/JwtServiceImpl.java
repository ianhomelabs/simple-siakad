package com.ianhomelabs.simple_siakad.service;

import com.ianhomelabs.simple_siakad.dto.JwtDto;
import com.ianhomelabs.simple_siakad.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.issuer}")
    private String issuer;

    @Override
    public String generateToken(HashMap<String, String> extraClaims) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(extraClaims.get("email"))
                .setIssuer(issuer)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public JwtDto extractToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return JwtDto.builder()
                    .exp(claimsJws.getBody().getExpiration())
                    .iat(claimsJws.getBody().getIssuedAt())
                    .id(UUID.fromString(claimsJws.getBody().get("id", String.class)))
                    .issuer(claimsJws.getBody().getIssuer())
                    .role(claimsJws.getBody().get("role", String.class))
                    .email(claimsJws.getBody().getSubject())
                    .build();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new UnauthorizedException("Unauthorized");
        }
    }
}
