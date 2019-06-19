package com.springboot.booxj.web;

import com.springboot.booxj.prepareBeanFactory.CustomizePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 14:30
 * @since
 */
@RestController
public class CustomizePublishEventController {

    @Autowired
    private CustomizePublisher customizePublisher;

    @RequestMapping("/publish")
    public String publish(){

        customizePublisher.publishEvent();

        return "publish finish, " + new Date();
    }

}
