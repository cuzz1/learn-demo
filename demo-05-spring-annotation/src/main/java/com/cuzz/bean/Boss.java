package com.cuzz.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 20:57
 * @Description:
 */
public class Boss {
    @Autowired
    private Car car;

    @Autowired
    public Boss(Car car) {
    }

    public Car getCar() {
        return car;
    }

    @Autowired
    public void setCar(@Autowired Car car) {
        this.car = car;
    }
}
