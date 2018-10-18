package com.cuzz.test01;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author: cuzz
 * @Date: 2018/10/18 13:29
 * @Description:
 */
public class Mian {

    public static void main(String[] args) {
    }

    @Test
    public void test03() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        //Integer sum = stream.reduce(0, Integer::sum);
        //System.out.println(sum);
        Optional<Integer> optionalInteger = stream.reduce((x, y) -> x + y);
        System.out.println(optionalInteger.get());


    }

    @Test
    public void test02() {
        Stream<Double> stream = Stream.generate(Math::random).limit(100);
        System.out.println(stream);
    }



    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // list.forEach(System.out::println);
        //list.stream().filter(i -> i > 3).forEach(System.out::println);
        list.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer > 3) {
                    return  true;
                }
                return false;
            }
        }).forEach(System.out::println);
    }


}
