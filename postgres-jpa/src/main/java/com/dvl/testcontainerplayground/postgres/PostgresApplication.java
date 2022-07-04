package com.dvl.testcontainerplayground.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dvl.testcontainerplayground.postgres.repositories")
public class PostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresApplication.class, args);
    }

}
