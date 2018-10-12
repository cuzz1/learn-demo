package com.cuzz.demo01;


import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Author: cuzz
 * @Date: 2018/10/12 20:10
 * @Description:
 */
public class MethodReference {

    @Test
    // 自动推导
    public void test01() {
        Function<String, Integer> function = Integer::parseInt;
        Integer i = function.apply("123");
        System.out.println(i);
    }

    @Test
    public void test02() {
        BiFunction<String, Long, Apple> appleBiFunction = Apple::new;
        Apple apple = appleBiFunction.apply("red", 100L);
        System.out.println(apple);

    }
}
