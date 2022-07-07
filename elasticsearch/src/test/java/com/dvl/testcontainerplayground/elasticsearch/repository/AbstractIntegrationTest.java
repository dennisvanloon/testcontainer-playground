package com.dvl.testcontainerplayground.elasticsearch.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
public abstract class AbstractIntegrationTest {

    private static final DockerImageName ES_IMAGE = DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.17.4");
    private static final ElasticsearchContainer elasticSearchContainer;

    static {
        elasticSearchContainer = new ElasticsearchContainer(ES_IMAGE)
                .withExposedPorts(9200);
        elasticSearchContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("elasticsearch.host.address", elasticSearchContainer::getHttpHostAddress);
    }
}