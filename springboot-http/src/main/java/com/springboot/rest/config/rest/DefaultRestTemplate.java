package com.springboot.rest.config.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Component
public class DefaultRestTemplate extends AbstactRestTemplate {


    public <T> T sendGet(String url, Class<T> responseType, String... header) {
        T response = getForObject(url, responseType);
        return response;

    }

    public <T> T sendPost(String url, Class<T> responseType, Map params, String... header) throws Exception {
        HttpEntity entity = getEntity(getHeader(header), params);
        T response = postForObject(url, entity, responseType);
        return response;

    }


    @Override
    public HttpHeaders getHeader(String... args) {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }


    @Override
    public HttpEntity getEntity(HttpHeaders headers, Map params) {

        if (params.isEmpty()) {
            return new HttpEntity(headers);
        }
        if (params instanceof MultiValueMap) {
            return new HttpEntity(params, headers);
        }

        if (params instanceof Map) {
            try {
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

                ObjectMapper mapper = new ObjectMapper();
                String param = mapper.writeValueAsString(params);
                return new HttpEntity(param, headers);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
