package com.springboot.annotation;

import com.springboot.annotation.anno.MyFilter;
import com.springboot.annotation.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(value = "com.springboot.annotation.anno", useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyFilter.class)
        })
public class AnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AnnotationApplication.class, args);
        TestService testService = context.getBean(TestService.class);
        System.out.println(testService);
    }
}
