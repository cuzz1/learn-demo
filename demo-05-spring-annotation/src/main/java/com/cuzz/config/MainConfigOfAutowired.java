package com.cuzz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 19:53
 * @Description:
 */
@Configuration
@ComponentScan(value = {"com.cuzz.dao", "com.cuzz.service", "com.cuzz.controller"})
public class MainConfigOfAutowired {
}
