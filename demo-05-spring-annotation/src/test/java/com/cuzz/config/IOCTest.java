package com.cuzz.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 13:03
 * @Description:
 */
public class IOCTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        // 获取所有bean定义的名字
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        // 获取所有bean定义的名字
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean == bean2);
    }
}
