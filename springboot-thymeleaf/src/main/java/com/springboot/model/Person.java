package com.springboot.model;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 11:19
 * @see:
 * @since:
 */
public class Person {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Min(18)
    private Integer age;

    public String getName() {
        return name;
    }

    public Person(){
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
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
}
