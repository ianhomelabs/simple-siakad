package com.ianhomelabs.simple_siakad.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from simple academic";
    }
}
