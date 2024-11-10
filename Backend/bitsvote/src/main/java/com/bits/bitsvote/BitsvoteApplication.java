package com.bits.bitsvote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BitsvoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitsvoteApplication.class, args);
		System.out.println("Running....");	
		}

}
