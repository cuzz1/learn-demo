package com.cuzz.demo03;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @Author: cuzz
 * @Date: 2018/10/14 15:04
 * @Description:
 */
public class ParallelProcessing {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    @Test
    public void test01() {
        System.out.println(iterateStream(10000L));
    }
}
