package com.ianhomelabs.simple_siakad.service;

import com.ianhomelabs.simple_siakad.dto.JwtDto;

import java.util.HashMap;

public interface JwtService {
    String generateToken(HashMap<String, String> extraClaims);

    JwtDto extractToken(String token);
}
