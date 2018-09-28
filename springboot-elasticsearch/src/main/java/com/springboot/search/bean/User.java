package com.springboot.search.bean;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 注解参数介绍
 * indexName：索引库的名称
 * type：类型
 * shards：分区数，default 5
 * replicas：每个分区默认的备份数，default 1
 * refreshInterval：刷新间隔，default "1s"
 * indexStoreType：索引文件存储类型，default "fs"
 */
@Document(indexName = "userindex",type = "user")
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private String description;
    private String created;


    public User(Long id, String name, Integer age, String description, String created) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.description = description;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
