package com.cuzz.demo02;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: cuzz
 * @Date: 2018/10/13 15:22
 * @Description:
 */
public class StreamMatch {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        // 所有的都大于0
        boolean b = stream.allMatch(i -> i > 0);

        // 只要有一个大于6
        boolean b1 = stream.anyMatch(i -> i > 6);

    }
}
