package com.springboot.jdbc.domain;

import java.util.Date;

/**
 * @ClassName: User.java
 * @Description: TODO
 * @Author: wangbo
 * @CreateDate 2019/5/9 13:44
 * @version:
 */
public class User {

    private int id;
    private String name;
    private int age;
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
