package com.example.practica4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TuApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TuApiApplication.class, args);
    }
}


