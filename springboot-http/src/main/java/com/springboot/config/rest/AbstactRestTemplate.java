package com.springboot.config.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;


public abstract class AbstactRestTemplate {

    @Autowired
    protected RestTemplate restTemplate = new RestTemplate();

    protected AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

    protected HttpHeaders headers = new HttpHeaders();

    HttpEntity<MultiValueMap<String, String>> entity;

    public <T> T getForObject(String url, Class<T> responseType) {
        setHeader();
        T response = restTemplate.getForObject(url, responseType);
        return response;
    }


    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) {
        setHeader();
        ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);
        return response;
    }


    public <T> T postForObject(String url, MultiValueMap params, Class<T> responseType) {
        setHeader();
        setEntity(params);
        T response = restTemplate.postForObject(url, entity, responseType);
        return response;
    }

    public <T> ResponseEntity<T> postForEntity(String url, MultiValueMap params, Class<T> responseType) {
        setHeader();
        setEntity(params);
        ResponseEntity<T> response = restTemplate.postForEntity(url, entity, responseType);
        return response;
    }

    public <T> ResponseEntity<T> asyncGetForEntity(String url, MultiValueMap params, Class<T> responseType) throws ExecutionException, InterruptedException {
        setHeader();
        setEntity(params);
        ListenableFuture<ResponseEntity<T>> future = asyncRestTemplate.getForEntity(url, responseType);
        future.addCallback(new SuccessCallback<ResponseEntity<T>>() {
            public void onSuccess(ResponseEntity<T> result) {
                System.out.println(result);
            }
        }, new FailureCallback() {
            public void onFailure(Throwable ex) {
                System.out.println("onFailure:" + ex);
            }
        });
        return future.get();
    }

    public <T> ResponseEntity<T> asyncPostForEntity(String url, MultiValueMap params, Class<T> responseType) throws ExecutionException, InterruptedException {
        setHeader();
        setEntity(params);
        ListenableFuture<ResponseEntity<T>> future = asyncRestTemplate.postForEntity(url, entity, responseType);
        future.addCallback(new SuccessCallback<ResponseEntity<T>>() {
            public void onSuccess(ResponseEntity<T> result) {
//                System.out.println(result);
            }
        }, new FailureCallback() {
            public void onFailure(Throwable ex) {
                System.out.println("onFailure:" + ex);
            }
        });
        return future.get();
    }

    protected abstract void setHeader();

    protected void setEntity(MultiValueMap params) {
        if (params.isEmpty()) {
            return;
        }
        this.entity = new HttpEntity<>(params, this.headers);
    }
}
