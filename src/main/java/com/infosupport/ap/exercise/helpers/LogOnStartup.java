package com.infosupport.ap.exercise.helpers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LogOnStartup {
    @Bean
    CommandLineRunner runAtStartup() {
        return args -> {
            System.out.println("I have started succesfully. You can open the Front-end on localhost:8080 and start your exercise.");
        };
    }
}
