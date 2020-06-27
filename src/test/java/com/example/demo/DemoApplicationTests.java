package com.example.demo;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest(classes = {DemoApplication.class})
class DemoApplicationTests {
    @Autowired
    RestHighLevelClient highLevelClient;


    @Test
    void contextLoads() {
    }

    @Test
    void testESClient() throws IOException {
        GetRequest getRequest= new GetRequest("kibana_sample_data_ecommerce", "V5z1f28BdseAsPClo7bC");
        GetResponse getResponse = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getIndex());
        System.out.println(getResponse.toString());
    }

}
