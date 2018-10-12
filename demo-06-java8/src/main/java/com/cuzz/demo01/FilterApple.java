package com.cuzz.demo01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Author: cuzz
 * @Date: 2018/10/12 13:00
 * @Description:
 */
public class FilterApple {

    @FunctionalInterface
    public interface AppleFilter {
        boolean filter(Apple apple);
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findGreenApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findGreenApple(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    // 直接写死
    @Test
    public void test01() {
        List<Apple> apples = Arrays.asList(new Apple("green", 100), new Apple("red", 120));
        List<Apple> greenApples = findGreenApple(apples);
        assert greenApples.size() == 1;
        System.out.println(greenApples);
    }

    // 带有参数
    @Test
    public void test02() {
        List<Apple> apples = Arrays.asList(new Apple("green", 100), new Apple("red", 120));
        List<Apple> greenApples = findGreenApple(apples, "green");
        assert greenApples.size() == 1;
        System.out.println(greenApples);
    }

    // 使用策略模式
    @Test
    public void test03() {

        class GreenAndGreater100WeightFiter implements AppleFilter {
            @Override
            public boolean filter(Apple apple) {
                return (apple.getColor().equals("green") && apple.getWeight() >= 100);
            }
        }

        List<Apple> apples = Arrays.asList(new Apple("green", 100), new Apple("red", 120), new Apple("green", 70));
        List<Apple> greenApples = findApple(apples, new GreenAndGreater100WeightFiter());
        assert greenApples.size() == 1;
        System.out.println(greenApples);
    }

    // 匿名类
    @Test
    public void test04() {
        List<Apple> apples = Arrays.asList(new Apple("green", 100), new Apple("red", 120), new Apple("green", 70));
        List<Apple> greenApples = findApple(apples, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        System.out.println(greenApples);
    }

    // 匿名类 如果interface只有一个方法
    @Test
    public void test05() {
        List<Apple> apples = Arrays.asList(new Apple("green", 100), new Apple("red", 120), new Apple("green", 70));
        List<Apple> greenApples = findApple(apples, apple -> {
           return apple.getColor().equals("green");
        });

        System.out.println(greenApples);
    }
}
