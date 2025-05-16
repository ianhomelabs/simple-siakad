package com.ianhomelabs.simple_siakad;

import com.ianhomelabs.simple_siakad.dto.JwtDto;
import com.ianhomelabs.simple_siakad.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class JwtTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void HelloTest() {
        log.info("HelloTest");
    }

    @Test
    public void GenerateTokenTest() {

        HashMap<String, String> extraClaims = new HashMap<>();
        extraClaims.put("role", "MAHASISWA");
        extraClaims.put("id", UUID.randomUUID().toString());
        extraClaims.put("email", "ian@media.com");

        String token = jwtService.generateToken(extraClaims);
        log.info("Token: {}", token);
    }

    @Test
    public void ExtractClaimsTest() {
        JwtDto jwtDto = jwtService.extractToken("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiTUFIQVNJU1dBIiwiaWQiOiI5NWUwOTM4NS1iMDIwLTRkNDItYWI1ZC1lNWMwMjg3NmFkYTMiLCJlbWFpbCI6ImlhbkBtZWRpYS5jb20iLCJzdWIiOiJpYW5AbWVkaWEuY29tIiwiaXNzIjoic2ltcGxlLXNpYWthZCIsImV4cCI6MTc0NzM2ODUzOSwiaWF0IjoxNzQ3MzY4NTM5fQ.D66DZL2lFRYWTYFpel_JFRHNFBtVnRcgGupSXBGkLG8");

        log.info("ID: {}", jwtDto.getId());
        log.info("Email: {}", jwtDto.getEmail());
        log.info("Role: {}", jwtDto.getRole());
        log.info("Issuer: {}", jwtDto.getIssuer());
        log.info("Exp: {}", jwtDto.getExp());
        log.info("Iat: {}", jwtDto.getIat());
    }
}
