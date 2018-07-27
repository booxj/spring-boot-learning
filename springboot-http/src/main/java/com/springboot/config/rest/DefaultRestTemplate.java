package com.springboot.config.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultRestTemplate extends AbstactRestTemplate {

    private HttpHeaders headers;
    private HttpEntity entity;

    public <T> T sendGet(String url, Class<T> responseType, String... header) {
        try {
            setHeader(header);
            setEntity(new HashMap());
            T response = getForObject(url, responseType);
            return response;
        } finally {
            release();
        }
    }

    public <T> T sendPost(String url, Class<T> responseType, Map params, String... header) throws Exception {
        try {
            setHeader(header);
            setEntity(params);
            T response = postForObject(url, this.entity, responseType);
            return response;
        } finally {
            release();
        }
    }

    private void release() {
        this.entity = null;
        this.headers = null;
    }

    @Override
    public void setHeader(String... args) {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }


    @Override
    public void setEntity(Map params) {

        if (params.isEmpty()) {
            entity = new HttpEntity(this.headers);
        }
        if (params instanceof MultiValueMap) {
            entity = new HttpEntity(params, this.headers);
        }

        if (params instanceof Map) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String param = mapper.writeValueAsString(params);
                entity = new HttpEntity(param, this.headers);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
