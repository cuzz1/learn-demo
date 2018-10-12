package com.cuzz.demo01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @Author: cuzz
 * @Date: 2018/10/12 18:35
 * @Description:
 */
public class LambdaUsage {



    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello"));
    }


    private static void process(Runnable r) {
        r.run();
    }


    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

    @Test
    public void test01() {
        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
        List<Apple> result = filter(list, apple -> apple.getColor().equals("green"));
        System.out.println(result);
    }

    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getWeight())) {
                result.add(a);
            }
        }
        return result;
    }

    @Test
    public void test02() {
        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
        List<Apple> result = filterByWeight(list, w -> w > 100);
        System.out.println(result);
    }
}
