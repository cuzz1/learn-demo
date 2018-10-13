package com.cuzz.demo02;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: cuzz
 * @Date: 2018/10/13 10:03
 * @Description:
 */
@Data
@AllArgsConstructor
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type { MEAT, FISH, OTHER }

}
