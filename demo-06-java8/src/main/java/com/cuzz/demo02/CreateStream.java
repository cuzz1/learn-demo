package com.cuzz.demo02;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: cuzz
 * @Date: 2018/10/13 14:37
 * @Description:
 */
public class CreateStream {

    // Generate the stream object from collection
    private Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("cuzz", "uzi", "faker");
        return list.stream();
    }

    // Generate the stream object from values
    private Stream<String> createStreamFromValues() {
        return Stream.of("cuzz", "uzi", "faker");
    }

    // Generate the stream object from arrays
    private Stream<String> createStreamFromArrays() {
        return Arrays.stream(new String[]{"cuzz", "uzi", "faker"});
    }
    @Test
    public void test01() {
        createStreamFromCollection().forEach(System.out::println);
        createStreamFromValues().forEach(System.out::println);
        createStreamFromArrays().forEach(System.out::println);
    }

}
