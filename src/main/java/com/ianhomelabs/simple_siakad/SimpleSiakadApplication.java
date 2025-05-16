package com.ianhomelabs.simple_siakad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SimpleSiakadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSiakadApplication.class, args);
	}

}
