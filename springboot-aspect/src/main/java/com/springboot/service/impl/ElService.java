package com.springboot.service.impl;

import org.springframework.stereotype.Service;

@Service
public class ElService {


    public String el() {
        System.out.println("I am a el!");
        return "el";
    }
}
