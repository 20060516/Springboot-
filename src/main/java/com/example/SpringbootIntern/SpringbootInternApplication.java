package com.example.SpringbootIntern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootInternApplication {

//	@Value("${app.jwt-Secret}")
//	private static String jwtSecret;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootInternApplication.class, args);
		//System.out.println("JWT KEY" + jwtSecret);
	}

}
