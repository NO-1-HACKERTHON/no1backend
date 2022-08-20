package com.no1.calender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class No1backendApplication {

    public static void main(String[] args) {
        SpringApplication.run(No1backendApplication.class, args);
    }

}
