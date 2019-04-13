package com.cuzz.aop;

import com.cuzz.config.MainConfig;
import com.cuzz.config.MainConfigOfAOP;
import com.cuzz.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestDemo3 {
    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
        userService.delete();
        userService.update();
        userService.get();
    }
}