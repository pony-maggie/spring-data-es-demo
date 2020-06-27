package com.example.demo;


import com.example.demo.domain.UserEsEntity;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = {DemoApplication.class})
public class EsReadWriteTest {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private DocumentOperations documentOperations;

    @Autowired
    private SearchOperations searchOperations;


    @Autowired
    RestHighLevelClient restHighLevelClient;

    @AfterEach
    public void tearDown() throws IOException {
        restHighLevelClient.close();
    }

    @Test
    public void testSave() {

        UserEsEntity user = new UserEsEntity();
        user.setLastName("王五");
        user.setAge(23);
        user.setBirthDate(LocalDate.ofYearDay(1996, 100));
        user.setId("3");
        user.setIsDeleted(false);
        user.setCreateTime(LocalDate.now());
        user.setUpdateTime(LocalDate.now());

        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(user.getClass());

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(user.getId())
                .withObject(user)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery, indexCoordinates);
    }

    @Test
    public void testQuery() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(new MatchAllQueryBuilder())
                .build();

        SearchHits<UserEsEntity> searchHits = searchOperations.search(searchQuery, UserEsEntity.class);
        long count = searchHits.getTotalHits();
        System.out.println(count);
        List<SearchHit<UserEsEntity>> list = searchHits.getSearchHits();
        for (SearchHit hit:list) {
            System.out.println(hit.getContent());
        }
    }


}
