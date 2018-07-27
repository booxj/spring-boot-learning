package com.springboot.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.config.rest.AbstactRestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@Component
public class HttpUtils extends AbstactRestTemplate {



    public static void main(String[] args) throws JsonProcessingException {

        HttpUtils utils = new HttpUtils();
////        String url = "http://localhost:8080/get?p=12321";
////        System.out.println(utils.getForEntity(url, String.class));
////        System.out.println(utils.getForObject(url, String.class));
//
//
        String url = "http://localhost:8080/post";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("p", "11");
        System.out.println(utils.postForObject(url, params, String.class));
        System.out.println(utils.postForEntity(url, params, String.class));




        /**********************get*******************************/



        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//
//        MultiValueMap<String, String> getParameters = new LinkedMultiValueMap<String, String>();
//        getParameters.add("p", "11");
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(getParameters, headers);
//
//        ResponseEntity<String> getResponse = restTemplate.getForEntity("http://localhost:8080/get", String.class);
//
//        System.out.println(getResponse);


        /**-------------post---------------------*/
        //  请求地址

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("p", "11");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(postParameters, headers);

        String postResponse = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(postResponse);

    }


    @Override
    protected void setHeader() {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }
}
