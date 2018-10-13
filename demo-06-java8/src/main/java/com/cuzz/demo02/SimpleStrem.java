package com.cuzz.demo02;

import org.junit.Test;

import java.io.PipedReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: cuzz
 * @Date: 2018/10/13 10:06
 * @Description:
 */
public class SimpleStrem {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCalories.add(d);
            }
        }
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        List<String> dishNmaes = new ArrayList<>();
        for (Dish d : lowCalories) {
            dishNmaes.add(d.getName());
        }
        return dishNmaes;
    }

    // .parallelStream可以并行
    private static List<String> getDishNamesByStream(List<Dish> menu) {
        return  menu.stream().filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
    }

    @Test
    public void test01() {
        List<String> dishNames = getDishNamesByCollections(menu);
        System.out.println(dishNames);
    }

    @Test
    public void test02() {
        List<String> dishNames= getDishNamesByStream(menu);
        System.out.println(dishNames);
    }
}
