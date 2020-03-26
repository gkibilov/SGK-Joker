package com.sgk.joker.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SGKJokerRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SGKJokerRestApplication.class, args);
    }

}
