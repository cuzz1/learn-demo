package com.cuzz.byteBuf;

import sun.management.Agent;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: cuzz
 * @Date: 2019/1/19 15:40
 * @Description:
 */
public class AtomicUpdateTest {
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Person> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        Person person = new Person();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(fieldUpdater.getAndIncrement(person) + " ");
            }).start();

        }

    }
}
class Person{
    volatile int age = 1;
}

