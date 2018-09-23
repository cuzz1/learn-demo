package com.cuzz.config;

import com.cuzz.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:55
 * @Description: 配置类
 */
@Configuration // 告诉Spring这是一个配置类
//@ComponentScan(value = "com.cuzz") // 指定包\
@ComponentScan(value = "com.cuzz",
        includeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM,
                classes = MyTypeFilter.class),
        useDefaultFilters = false)
public class MainConfig {
    // 给容器中注册一个Bean,类型为返回值类型,id默认用方法名
    // 也可以指定id
//    @Bean(value = "person01")
//    public Person person() {
//        return new Person("vhsj", 16);
//    }
}
