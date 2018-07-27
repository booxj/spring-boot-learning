package com.springboot.utils;

import com.springboot.config.rest.DefaultRestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpUtilsTest {

    @Autowired
    private DefaultRestTemplate restTemplate;

    HttpEntity entity;
    HttpHeaders headers;


    @Test
    public void AsyncPostTest() throws InterruptedException, ExecutionException {
        String url = "http://localhost:8080/post";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("p", "11");
        entity = new HttpEntity(params, headers);

        ResponseEntity<String> res = restTemplate.asyncPostForEntity(url, entity, String.class);

        System.out.println("sleeping 5s");
        Thread.sleep(5000);
        System.out.println(res.getBody());
    }


    @Test
    public void PostTest() throws Exception {
        String url = "http://localhost:8080/post";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("p", "11");

        entity = new HttpEntity(params, headers);
        String res = restTemplate.sendPost(url, String.class, params);
        System.out.println(res);
    }
}
