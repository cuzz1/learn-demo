package com.cuzz.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 13:36
 * @Description:
 */
public class Cat implements InitializingBean, DisposableBean{

    public Cat() {
        System.out.println("cat constructor...");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat...init...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("cat...destroy...");
    }

}
