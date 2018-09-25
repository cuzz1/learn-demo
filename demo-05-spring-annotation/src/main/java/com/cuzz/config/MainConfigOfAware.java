package com.cuzz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cuzz
 * @Date: 2018/9/25 10:26
 * @Description:
 */
@Configuration
@ComponentScan(value = {"com.cuzz.bean"})
public class MainConfigOfAware {
}
