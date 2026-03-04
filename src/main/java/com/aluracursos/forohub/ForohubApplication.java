package com.aluracursos.forohub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ForohubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

//    @Bean
//    public CommandLineRunner generator() {
//        return args -> {
//            System.out.println("HASH BCRYPT: " + new BCryptPasswordEncoder().encode("123456"));
//        };
//    }
}
