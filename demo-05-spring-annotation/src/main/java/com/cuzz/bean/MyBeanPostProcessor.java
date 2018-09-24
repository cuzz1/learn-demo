package com.cuzz.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 14:21
 * @Description: 后置处理器，初始化前后进行处理工作
 */
public class MyBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("--->postProcessBeforeInitialization..." + beanName +"==>" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("--->postProcessAfterInitialization..." + beanName +"==>" + bean);
        return bean;
    }
}
