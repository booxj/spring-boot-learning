package com.springboot.starter;

import com.springboot.servicestarter.annotation.EnableStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 9:35
 * @since
 */
//@EnableStarter
@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
