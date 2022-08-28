package com.br.schoolreyfowlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SchoolReyfowLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolReyfowLoginApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("senha123"));

    }

}
