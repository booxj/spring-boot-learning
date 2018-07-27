package com.springboot.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private HttpUtils httpUtils;


    @Test
    public void httpTest() throws InterruptedException, ExecutionException {
        String url = "http://localhost:8080/post";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("p", "11");
        ResponseEntity<String> res = httpUtils.asyncPostForEntity(url, params, String.class);

        System.out.println("sleeping 5s");
        Thread.sleep(5000);
        System.out.println(res.getBody());
    }
}
