package com.cuzz.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 21:15
 * @Description:
 */
public class MyImportSelector implements ImportSelector{

    // 返回值就导入容器组件的全类名
    // AnnotationMetadata:当前类标注的@Import注解类的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"com.cuzz.bean.Car"};
    }
}
