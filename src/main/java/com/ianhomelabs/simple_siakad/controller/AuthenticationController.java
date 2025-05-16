package com.ianhomelabs.simple_siakad.controller;

import com.ianhomelabs.simple_siakad.service.AuthenticationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login/mahasiswa")
    public String loginMahasiswa(String username, String password) {
        return authenticationService.loginMahasiswa(username, password);
    }

    @GetMapping("/login/dosen")
    public String loginDosen(String username, String password) {
        return authenticationService.loginDosen(username, password);
    }

}
