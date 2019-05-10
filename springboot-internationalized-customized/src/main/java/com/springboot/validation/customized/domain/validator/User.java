package com.springboot.validation.customized.domain.validator;

import java.util.Date;


public class User {

    private long id;

    private String name;

    private Date create;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}
