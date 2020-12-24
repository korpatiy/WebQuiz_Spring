package com.example.webquiz;

import com.example.webquiz.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class WebQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizApplication.class, args);
    }
}
