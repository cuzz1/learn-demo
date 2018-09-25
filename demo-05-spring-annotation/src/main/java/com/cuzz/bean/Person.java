package com.cuzz.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:42
 * @Description:
 */
@Data
public class Person {

    @Value("vhuj")
    private String name;

    @Value("#{20-2}")
    private Integer age;

    @Value("${person.nickName}")
    private String nickName;

    public Person() {

    }
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Person(String name, Integer age, String nickName) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
    }

}

