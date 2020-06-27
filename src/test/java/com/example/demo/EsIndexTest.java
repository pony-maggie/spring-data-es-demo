package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootTest(classes = {DemoApplication.class})
public class EsIndexTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    @Test
    public void testIndex() {
    }
}
