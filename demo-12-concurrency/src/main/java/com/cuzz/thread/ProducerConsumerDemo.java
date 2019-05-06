package com.cuzz.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-05-01 12:02
 **/

public class ProducerConsumerDemo {
    public static void main(String[] args) {

        ProducerConsumerDemo producerConsumerDemo = new ProducerConsumerDemo();
        System.out.println(Runtime.getRuntime().availableProcessors());
//        new Thread(producerConsumerDemo::produce).start();
//        new Thread(producerConsumerDemo::comsume).start();

    }
    private final int MAX_SIZE= 5;
    private List<Integer> list = new ArrayList<>();
    Random random = new Random(10);

    private void produce() {
        while (true) {
            synchronized (this) {
                while (list.size() == 5) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int i = random.nextInt();
                list.add(i);
                System.out.println("produce..." + i);
                notify();
            }
        }
    }

    private int comsume() {
        while (true) {
            synchronized (this) {
                while (list.size() == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer remove = list.remove(0);
                System.out.println("consume..." + remove);
                notify();
            }

        }

    }


}

