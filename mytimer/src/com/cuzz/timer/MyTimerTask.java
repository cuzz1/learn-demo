package com.cuzz.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        // 以yy-MM-dd HH:mm:ss的格式打印执行的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String str = sdf.format(new Date());
        System.out.println("current time is: " + str);
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
