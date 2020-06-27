package com.example.demo;


import com.example.demo.domain.UserEsEntity;
import com.example.demo.repository.UserEsRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = {DemoApplication.class})
public class EsReadWriteRepositoryTest {
    @Autowired
    private UserEsRepository repository;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @AfterEach
    public void tearDown() throws IOException {
        restHighLevelClient.close();
    }



    @Test
    public void testSave() {
        UserEsEntity entity = new UserEsEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setAge(50);
        entity.setLastName("东野圭吾");
        entity.setBirthDate(LocalDate.ofYearDay(1964, 200));
        entity.setCreateTime(LocalDate.now());
        entity.setUpdateTime(LocalDate.now());
        entity.setIsDeleted(false);
        repository.save(entity);
    }

    @Test
    public void testFindAll() {
        long count = repository.count();
        System.out.println(count);
        Iterable<UserEsEntity> result = repository.findAll();
        Iterator<UserEsEntity> data = result.iterator();
        while (data.hasNext()) {
            System.out.println(data.next());
        }
    }

    @Test
    public void testFindById() {
        Optional<UserEsEntity> data = repository.findById("5c7ca0b7-4236-48f1-8ed4-8ce9555092d8");
        System.out.println(data.get());
    }
}
