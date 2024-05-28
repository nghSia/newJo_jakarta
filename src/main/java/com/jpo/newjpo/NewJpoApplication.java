package com.jpo.newjpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.jpo.newjpo"})
@EnableJpaRepositories(basePackages = "com.jpo.newjpo.repository")

public class NewJpoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewJpoApplication.class, args);
    }

}
