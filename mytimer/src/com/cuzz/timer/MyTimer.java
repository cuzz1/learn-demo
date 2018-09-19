package com.cuzz.timer;

import java.util.Timer;

/**
 * @Author: cuzz
 * @Date: 2018/9/19 14:24
 * @Description:
 */
public class MyTimer {
    public static void main(String[] args) {
        // 1. 创建一个timer实例
        Timer timer = new Timer();
        // 2. 创建一个MyTimerTask实例
        MyTimerTask myTimerTask = new MyTimerTask("cuzz");
        // 3. 通过timer定时定频率调用myTimerTast的业务逻辑
        // 任务 延迟时间 间隔时间
        timer.schedule(myTimerTask, 2000L, 1000L);
    }
}
