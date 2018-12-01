package com.springboot.application;

import com.springboot.application.event.MyEvent;
import com.springboot.application.event.MyEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/publisher")
    public String publisher(){
        publisher.publishEvent(new MyEvent(""));
        return "发布了一个自定义事件";
    }

    public static void main(String[] args) {

        /********** SpringApplication启动 **********/
        SpringApplicationStart(args);


        /********** SpringApplicationBuilder启动 **********/
//        SpringApplicationBuilderStart(args);
    }

    private static void SpringApplicationStart(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("server.port", 0);
        springApplication.setDefaultProperties(properties);

        //设置为 非 web 应用
//        springApplication.setWebApplicationType(WebApplicationType.NONE);

        springApplication.addListeners(new MyEventListener());
        springApplication.run(args);
    }

    private static void SpringApplicationBuilderStart(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .listeners(event-> System.err.println("监听到事件 ： " + event.getClass().getSimpleName()))
                .properties("server.port=0")
                .run(args).close();
    }

}
