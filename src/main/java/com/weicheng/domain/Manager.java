package com.weicheng.domain;

/**
 * Created by Weicheng on 9/10/2018.
 */
public class Manager {
    private String name;
    private Integer years;

    private Manager() {
    }

    public Manager(String name, Integer years) {
        this.name = name;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public Integer getYears() {
        return years;
    }
}
