package com.cuzz.bean;

import lombok.Data;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:42
 * @Description:
 */
@Data
public class Person {

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private String name;

    private Integer age;
}
