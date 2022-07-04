package com.dvl.testcontainerplayground.postgres.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

@SpringBootTest
public abstract class AbstractIntegrationTest {

    private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:bullseye");
    private static final GenericContainer postgresContainer;
    private static final String postgrespw = UUID.randomUUID().toString();

    static {
        postgresContainer = new GenericContainer(POSTGRES_IMAGE)
                .withEnv("POSTGRES_PASSWORD", postgrespw)
                .withExposedPorts(5432);
        postgresContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {

        registry.add("spring.jpa.properties.hibernate.dialect",() -> "org.hibernate.dialect.PostgreSQLDialect");
        registry.add("spring.jpa.hibernate.ddl-auto",() -> "create");
        registry.add("spring.jpa.hibernate.show-sql",() -> "true");
        registry.add("spring.datasource.url",() -> "jdbc:postgresql://localhost:" + postgresContainer.getMappedPort(5432) + "/postgres");
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> postgrespw);
    }
}