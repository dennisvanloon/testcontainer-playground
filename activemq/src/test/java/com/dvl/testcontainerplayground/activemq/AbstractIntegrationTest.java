package com.dvl.testcontainerplayground.activemq;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
public abstract class AbstractIntegrationTest {

    private static final DockerImageName ACTIVEMQ_IMAGE = DockerImageName.parse("rmohr/activemq:5.15.9");
    private static final GenericContainer activeMqContainer;

    static {
        activeMqContainer = new GenericContainer(ACTIVEMQ_IMAGE).withExposedPorts(61616);
        activeMqContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.activemq.broker-url", () -> "tcp://localhost:" + activeMqContainer.getMappedPort(61616));
    }
}