package com.stoldo.m223_punchclock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// TODO dependecy stuff -> wenn noch zeit eintrag mit kategire und kategrie löschen dann fehler etc. does this make sense tho?
// -> think about it
// TODO introdice error codes


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
