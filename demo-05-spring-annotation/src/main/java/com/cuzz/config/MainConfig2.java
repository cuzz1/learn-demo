package com.cuzz.config;

import com.cuzz.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 15:40
 * @Description:
 */
@Configuration
public class MainConfig2 {

    @Scope(value = "prototype")
    @Bean
    public Person person() {
        return new Person("vhuj", 25);
    }
}
