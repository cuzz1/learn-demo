package com.cuzz.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-05-01 16:36
 **/

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 在 FutureTask 中传入 Callable 的实现类
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 666;
            }
        });
        // 把 futureTask 放入线程中
        new Thread(futureTask).start();
        // 获取结果
        Integer res = futureTask.get();
        System.out.println(res);
    }
}
