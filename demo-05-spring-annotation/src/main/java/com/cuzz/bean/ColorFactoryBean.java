package com.cuzz.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 21:55
 * @Description: Spring定义的工厂Bean
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    // 返回一个Color对象
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    // 是否为单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
