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
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }
}
