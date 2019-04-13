package com.cuzz.config;

import com.cuzz.bean.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 13:03
 * @Description:
 */
public class IOCTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        // // 获取所有bean定义的名字
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
        Person person1 = (Person) applicationContext.getBean("person01");
        System.out.println(person1);
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        // 获取所有bean定义的名字
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
        System.out.println("ioc容器创建完成...");
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean == bean2);
    }

    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        System.out.println("ioc容器创建完成...");
        Object bean = applicationContext.getBean("person");
    }
    @Test
    public void test04() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        // 获取环境变量
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);

        // 获取所有bean定义的名字
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }

        // key 是id
        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);
    }

    @Test
    public void test05() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        Object bean = applicationContext.getBean("colorFactoryBean");
        // 工厂bean调用的是getClass()方法
        System.out.println("colorFactoryBean的类型是: " + bean.getClass());
        Object bean2 = applicationContext.getBean("&colorFactoryBean");
        // 工厂bean调用的是getClass()方法
        System.out.println("colorFactoryBean的类型是: " + bean2.getClass());
    }
}
