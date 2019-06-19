package com.springboot.booxj;

import com.springboot.booxj.service.ImportService1;
import com.springboot.booxj.service.ImportService2;
import com.springboot.booxj.service.ImportService3;
import com.springboot.booxj.service.ImportService4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 10:59
 * @since
 */
@SpringBootApplication
@RestController
public class ImportSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImportSampleApplication.class, args);
    }


    @Autowired(required = false)
    ImportService1 importService1;

    @Autowired(required = false)
    ImportService2 importService2;

    @Autowired(required = false)
    ImportService3 importService3;

    @Autowired(required = false)
    ImportService4 importService4;

    @GetMapping("hello")
    public String hello(){
        importService1.execute();
        importService2.execute();
        importService3.execute();
        importService4.execute();
        return "finish";
    }

}
