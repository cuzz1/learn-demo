package com.cuzz.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:48
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 用id获取
        Person bean = (Person) applicationContext.getBean("person");
        System.out.println(bean);
    }
}
