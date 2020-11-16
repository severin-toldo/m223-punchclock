package com.stoldo.m223_punchclock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
@SpringBootApplication
public class ServerMainController {

    public static void main(String[] args) {
        SpringApplication.run(ServerMainController.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
