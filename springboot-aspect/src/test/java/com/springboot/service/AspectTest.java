package com.springboot.service;

import com.springboot.service.impl.AnnotationService;
import com.springboot.service.impl.ElService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectTest {

    @Autowired
    private ElService elService;

    @Autowired
    private AnnotationService annotationService;


    @Test
    public void elTest(){
        elService.el();
    }

    @Test
    public void annotationServiceTest(){
        annotationService.annotation();
    }
}
