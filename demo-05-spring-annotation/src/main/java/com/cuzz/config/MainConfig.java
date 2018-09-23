package com.cuzz.config;

import com.cuzz.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:55
 * @Description: 配置类
 */
@Configuration // 告诉Spring这是一个配置类
public class MainConfig {
    // 给容器中注册一个Bean,类型为返回值类型,id默认用方法名
    // 也可以指定id
    @Bean(value = "person01")
    public Person person() {
        return new Person("vhsj", 16);
    }
}
