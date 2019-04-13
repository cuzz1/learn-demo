package com.cuzz.config;

import com.cuzz.aop.MyAdvice;
import com.cuzz.service.UserService;
import com.cuzz.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: cuzz
 * @Date: 2019/2/10 20:43
 * @Description:
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public MyAdvice myAdvice() {
        return new MyAdvice();
    }
}
