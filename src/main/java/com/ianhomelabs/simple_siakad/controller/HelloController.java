package com.ianhomelabs.simple_siakad.controller;

import com.ianhomelabs.simple_siakad.exception.BadRequestException;
import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from simple academic";
    }

    @GetMapping("/not-found")
    public String notFound(){
        throw new DataNotFoundException("Data yang anda cari tidak ditemukan");
    }

    @GetMapping("/bad-request")
    public String badRequest(){
        throw new BadRequestException("Data yang anda masukkan tidak valid");
    }
}
