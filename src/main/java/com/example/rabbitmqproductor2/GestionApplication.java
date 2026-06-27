package com.example.rabbitmqproductor2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // <-- AGREGADO
public class GestionApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionApplication.class, args);
    }
}
