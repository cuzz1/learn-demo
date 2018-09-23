package com.cuzz.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 20:30
 * @Description:
 */
public class LinuxCondition implements Condition{
    /**
     * @param context 判断条件
     * @param metadata 注释信息
     * @return boolean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if (property.contains("Linux")) {
            return true;
        }
        return false;
    }
}
