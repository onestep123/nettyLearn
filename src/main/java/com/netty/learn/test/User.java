package com.netty.learn.test;


public class User {

    private String name;

    private Integer age;

    private Boolean isBig;

    public User(String name, Integer age, Boolean isBig) {
        this.name = name;
        this.age = age;
        this.isBig = isBig;
    }

    public Boolean getBig() {
        return isBig;
    }

    public void setBig(Boolean big) {
        isBig = big;
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

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
