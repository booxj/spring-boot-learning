package com.springboot.model;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 10:49
 * @see:
 * @since:
 */
public class Account {

    private int id ;
    private String name ;
    private double money;


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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
