package com.cuzz.timer;

import java.util.TimerTask;

/**
 * @Author: cuzz
 * @Date: 2018/9/19 14:19
 * @Description:
 */
public class MyTimerTask extends TimerTask{

    private String name;

    public MyTimerTask(String inputName) {
        this.name = inputName;
    }

    @Override
    public void run() {
        // 打印当前name
        System.out.println("current name is: " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
