package com.cuzz.thread;

import java.util.concurrent.*;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-05-06 23:09
 **/

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        Executor executor = new ThreadPoolExecutor(2, 3, 1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
    }
}
