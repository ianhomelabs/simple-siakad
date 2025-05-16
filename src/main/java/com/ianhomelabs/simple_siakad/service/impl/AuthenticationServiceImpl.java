package com.ianhomelabs.simple_siakad.service.impl;

import com.ianhomelabs.simple_siakad.enums.RoleType;
import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import com.ianhomelabs.simple_siakad.exception.UnauthorizedException;
import com.ianhomelabs.simple_siakad.model.Mahasiswa;
import com.ianhomelabs.simple_siakad.repository.DosenRepository;
import com.ianhomelabs.simple_siakad.repository.MahasiswaRepository;
import com.ianhomelabs.simple_siakad.service.AuthenticationService;
import com.ianhomelabs.simple_siakad.service.JwtService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final MahasiswaRepository mahasiswaRepository;
    private final DosenRepository dosenRepository;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(MahasiswaRepository mahasiswaRepository, DosenRepository dosenRepository, JwtService jwtService) {
        this.mahasiswaRepository = mahasiswaRepository;
        this.dosenRepository = dosenRepository;
        this.jwtService = jwtService;
    }

    @Override
    public String loginMahasiswa(String username, String password) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByEmail(username)
                .orElseThrow(() -> new DataNotFoundException("Mahasiswa tidak ditemukan"));
        if (!BCrypt.checkpw(password, mahasiswa.getPassword())) {
            throw new UnauthorizedException("Username atau password salah");
        }

        return jwtService.generateToken(new HashMap<>() {{
            put("id", mahasiswa.getId().toString());
            put("role", RoleType.MAHASISWA.name());
            put("email", mahasiswa.getEmail());
        }});
    }

    @Override
    public String loginDosen(String username, String password) {
        var dosen = dosenRepository.findByEmail(username)
                .orElseThrow(() -> new DataNotFoundException("Dosen tidak ditemukan"));
        if (!BCrypt.checkpw(password, dosen.getPassword())) {
            throw new UnauthorizedException("Username atau password salah");
        }

        return jwtService.generateToken(new HashMap<>() {{
            put("id", dosen.getId().toString());
            put("role", RoleType.DOSEN.name());
            put("email", dosen.getEmail());
        }});
    }
}
