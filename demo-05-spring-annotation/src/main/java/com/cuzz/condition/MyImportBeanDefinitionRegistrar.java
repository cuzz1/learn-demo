package com.cuzz.condition;

import com.cuzz.bean.Petrol;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 21:29
 * @Description:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * @param importingClassMetadata 当前类的注解信息
     * @param registry 注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 查询容器
        boolean b = registry.containsBeanDefinition("com.cuzz.bean.Car");
        // 如果有car, 注册一个汽油类
        if (b == true) {
            // 需要添加一个bean的定义信息
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Petrol.class);
            // 注册一个bean, 指定bean名
            registry.registerBeanDefinition("petrol", rootBeanDefinition);
        }

    }
}
