package com.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 18:10
 * @see:
 * @since:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
