package net.travelbook.infrastructure.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("SPRING BOOT HAS BEEN STARTED !!!");
        };
    }
}
