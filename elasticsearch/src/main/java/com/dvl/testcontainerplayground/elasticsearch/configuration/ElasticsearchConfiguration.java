package com.dvl.testcontainerplayground.elasticsearch.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestHighLevelClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.dvl.testcontainerplayground.elasticsearch.repository")
public class ElasticsearchConfiguration {

    private final Environment env;

    public ElasticsearchConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        HttpHost httpHost = HttpHost.create(env.getProperty("elasticsearch.host.address"));
        RestClient httpClient = RestClient.builder(httpHost).build();
        return  new RestHighLevelClientBuilder(httpClient).build();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(restHighLevelClient());
    }
}