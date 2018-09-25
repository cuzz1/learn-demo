package com.cuzz.config;

import com.cuzz.bean.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 18:46
 * @Description:
 */
public class IOCTestPropertyValue {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);

    @Test
    public void test01() {
        printBean(applicationContext);
        System.out.println("---------------------------");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);


        System.out.println("---------------------------");

    }


    private void printBean(AnnotationConfigApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
    }
}
