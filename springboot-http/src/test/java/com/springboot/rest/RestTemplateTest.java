package com.springboot.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;


/**
 * RestTemplate 方法测试，需要启动  {@link HttpApplication HttpApplication}
 */
public class RestTemplateTest {

    private RestTemplate restTemplate;

    private static final String URI = "http://localhost:8080/rest";
    private static MultiValueMap multiValueMap = new LinkedMultiValueMap();

    @Before
    public void before() {
        // 1.初始化 messageConverters
        restTemplate = new RestTemplate();
        // 2.初始化 interceptors
        restTemplate.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                traceRequest(request, body);
                ClientHttpResponse response = execution.execute(request, body);
                traceResponse(response);
                return response;
//                return execution.execute(request, body);
            }
        }));

        // 3.初始化参数值
        multiValueMap.add("id", 1 + "");
        multiValueMap.add("name", "booxj");
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        System.out.printf("=========================== request begin ===========================\n");
        System.out.printf("URI         : %s\n", request.getURI());
        System.out.printf("Method      : %s\n", request.getMethod());
        System.out.printf("Headers     : %s\n", request.getHeaders());
        System.out.printf("Request body: %s\n", new String(body, "UTF-8"));
        System.out.printf("=========================== request end ===========================\n\n\n");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
//        StringBuilder inputStringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
//        String line = bufferedReader.readLine();
//        while (line != null) {
//            inputStringBuilder.append(line);
//            inputStringBuilder.append('\n');
//            line = bufferedReader.readLine();
//        }

        System.out.printf("=========================== response begin ===========================\n");
        System.out.printf("Status code  : %s\n", response.getStatusCode());
        System.out.printf("Status text  : %s\n", response.getStatusText());
        System.out.printf("Headers      : %s\n", response.getHeaders());
        System.out.printf("Response body: %s\n", response.getBody());
        System.out.printf("=========================== response end ===========================\n\n\n");
    }

    @Test
    public void GetTest() {
//        System.out.println(restTemplate.getForObject(URI + "/{id}?name=booxj", String.class,"1"));

        System.out.println(restTemplate.exchange(URI + "/{id}", HttpMethod.GET, HttpEntity.EMPTY, String.class, "2"));
    }

    @Test
    public void PostTest() {
        System.out.println(restTemplate.postForEntity(URI, multiValueMap, String.class));
    }

    @Test
    public void PutTest() {
        System.out.println(restTemplate.exchange(URI + "/{id}", HttpMethod.PUT, getEntity(multiValueMap), String.class, "1"));
    }

    @Test
    public void DeleteTest() {
        System.out.println(restTemplate.exchange(URI + "/{id}", HttpMethod.DELETE, getEntity(multiValueMap), String.class, "1"));
    }


    public HttpEntity getEntity(MultiValueMap params) {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity(params, headers);
    }
}
