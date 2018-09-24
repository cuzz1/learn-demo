package com.cuzz.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 14:03
 * @Description:
 */
public class Dog {

    public Dog() {
        System.out.println("dog constructor...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("post construct...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("pre destroy...");
    }
}
