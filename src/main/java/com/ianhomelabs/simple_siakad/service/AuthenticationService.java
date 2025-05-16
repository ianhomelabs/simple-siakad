package com.ianhomelabs.simple_siakad.service;

public interface AuthenticationService {
    String loginMahasiswa(String username, String password);
    String loginDosen(String username, String password);
}
