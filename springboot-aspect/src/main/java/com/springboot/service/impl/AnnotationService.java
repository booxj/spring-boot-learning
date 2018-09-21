package com.springboot.service.impl;

import com.springboot.config.Aop;
import org.springframework.stereotype.Service;

@Service
public class AnnotationService {

    @Aop
    public String annotation(){
        System.out.println("I am a annotation!");
        return "annotation";
    }

}
