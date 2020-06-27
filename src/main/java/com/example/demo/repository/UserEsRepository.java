package com.example.demo.repository;


import com.example.demo.domain.UserEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserEsRepository extends ElasticsearchRepository<UserEsEntity, String> {
}
