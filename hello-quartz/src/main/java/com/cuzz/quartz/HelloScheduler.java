package com.cuzz.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author: cuzz
 * @Date: 2018/9/20 14:34
 * @Description:
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        // 创建一个JobDetail实例，将该实例与HelloJob Class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myjob", "group1").build();
        // 创建一个Trigger实例，定义该job立即执行, 并且每隔两秒中重复执行一次
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myjob", "mygoup")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2)
                                .repeatForever())
                .build();
        // 创建Scheduler实例
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
