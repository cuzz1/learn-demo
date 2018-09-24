package com.cuzz.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 13:00
 * @Description:
 */
public class IOCTestLifeCycle {

    @Test
    public void test01() {
        // 创建ioc容器
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfLifecycle.class);
        System.out.println("容器创建完成...");
        // 关闭容器
        System.out.println("--->开始关闭容器");
        applicationContext.close();
        System.out.println("--->已经关闭容器");
    }
}
