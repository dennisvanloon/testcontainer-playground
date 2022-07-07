package com.dvl.testcontainerplayground.elasticsearch.repository;

import com.dvl.testcontainerplayground.elasticsearch.model.Article;
import com.dvl.testcontainerplayground.elasticsearch.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ArticleRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void findByAuthorsName() {
        Article article = Article.builder()
                .title("Spring Data Elasticsearch")
                .authors(Arrays.asList(new Author("John Smith"), new Author("John Doe")))
                .build();
        articleRepository.save(article);

        Page<Article> articlesPage = articleRepository.findByAuthorsName("Smith", PageRequest.of(0, 2));
        assertEquals(1, articlesPage.getTotalElements());
        assertEquals(1, articlesPage.getTotalPages());
        assertEquals(1, articlesPage.getContent().size());
        assertEquals("Spring Data Elasticsearch", articlesPage.getContent().get(0).getTitle());


        articlesPage = articleRepository.findByAuthorsNameUsingCustomQuery("Smith", PageRequest.of(0, 2));
        assertEquals(1, articlesPage.getTotalElements());
        assertEquals(1, articlesPage.getTotalPages());
        assertEquals(1, articlesPage.getContent().size());
        assertEquals("Spring Data Elasticsearch", articlesPage.getContent().get(0).getTitle());
    }


}
