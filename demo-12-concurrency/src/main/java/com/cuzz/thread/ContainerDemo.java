package com.cuzz.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-04-22 23:17
 **/

public class ContainerDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        new Vector<>();
        Collections.synchronizedList(new ArrayList<>());
        new CopyOnWriteArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(random.nextInt(10));
                System.out.println(list);
            }).start();
        }
    }
}
