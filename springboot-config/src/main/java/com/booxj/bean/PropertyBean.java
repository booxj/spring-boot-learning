package com.booxj.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 9:26
 * @see:
 * @since:
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@ConfigurationProperties(prefix = "prefix")
public class PropertyBean {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
