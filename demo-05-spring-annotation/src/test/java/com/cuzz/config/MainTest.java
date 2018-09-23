package com.cuzz.config;

import com.cuzz.bean.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:59
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) context.getBean(Person.class);
        System.out.println(person);

        String[] names = context.getBeanNamesForType(Person.class);
        for (String name: names) {
            System.out.println(name);
        }
    }
}
