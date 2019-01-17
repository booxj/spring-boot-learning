package com.springboot.bean.valid.domain;

import com.springboot.bean.valid.annontation.AgeValid;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class User {

    @Max(100)
    private Long id;

    @AgeValid
    private Integer age;

    @NotNull
    private String idCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}

