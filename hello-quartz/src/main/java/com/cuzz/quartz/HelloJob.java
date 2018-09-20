package com.cuzz.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: cuzz
 * @Date: 2018/9/20 14:29
 * @Description:
 */
public class HelloJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 打印打印当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
        String str = sdf.format(new Date());
        System.out.println("current execute time is " + str);
        // 编写具体的业务逻辑
        System.out.println("Hello World");
    }
}
