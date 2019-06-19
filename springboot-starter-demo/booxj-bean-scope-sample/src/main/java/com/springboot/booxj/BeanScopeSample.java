package com.springboot.booxj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * spring 加载流程 {@link AbstractApplicationContext#prepareRefresh()}
 *
 */
@SpringBootApplication
public class BeanScopeSample {


    public static void main(String[] args) {
        SpringApplication.run(BeanScopeSample.class, args);
    }
}
