package com.weicheng.domain;

/**
 * Created by Weicheng on 9/10/2018.
 */
public class Player {
    private String name;
    private Integer age;

    public Player(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
