package com.cuzz.demo03;

import com.cuzz.demo01.Apple;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: cuzz
 * @Date: 2018/10/14 12:14
 * @Description:
 */
public class CollectorIntroduce {


    List<Apple> list = Arrays.asList(
            new Apple("green", 100),
            new Apple("green", 120),
            new Apple("red", 100),
            new Apple("green", 90),
            new Apple("red", 100)
    );

    @Test
    public void test01() {
        list.stream().filter(apple -> apple.getColor().equals("green"))
                .collect(Collectors.toList())
                .forEach(System.out::print);
    }

    @Test
    public void test02() {
        Map<String, List<Apple>> stringListMap = groupByNormal(list);
        System.out.println(stringListMap);

    }

    @Test
    public void test03() {
        Map<String, List<Apple>> stringListMap = groupByFunction(list);
        System.out.println(stringListMap);

    }

    @Test
    public void test04() {
        Map<String, List<Apple>> stringListMap = groupByCollector(list);
        System.out.println(stringListMap);

    }

    private Map<String, List<Apple>> groupByNormal(List<Apple> list) {
        HashMap<String, List<Apple>> map = new HashMap<>();
        for (Apple apple : list) {
            List<Apple> apples = map.get(apple.getColor());
            if (apples == null) {
                apples = new ArrayList<>();
                apples.add(apple);
                map.put(apple.getColor(), apples);
            }
            apples.add(apple);
        }
        return map;
    }

    private Map<String, List<Apple>> groupByFunction(List<Apple> list) {
        HashMap<String, List<Apple>> map = new HashMap<>();
        list.stream().forEach(apple -> {
            List<Apple> apples1 = Optional.ofNullable(map.get(apple.getColor())).orElseGet(() -> {
                List<Apple> apples = new ArrayList<>();
                map.put(apple.getColor(), apples);
                return apples;
            });
            apples1.add(apple);
        });
        return map;
    }
    private Map<String, List<Apple>> groupByCollector(List<Apple> list) {
        return list.stream().collect(Collectors.groupingBy(Apple::getColor));

    }


}
