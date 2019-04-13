package com.cuzz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

/**
 * @Author: cuzz
 * @Date: 2018/9/25 10:47
 * @Description:
 */
@Configuration
public class MainConfigOfProfile {

    @Profile(value = "test")
    @Bean(value = "testDataSource")
    public DataSource testDataSource() {

        System.out.println("testDataSource");
        return null;
    }

    @Profile(value = "dev")
    @Bean(value = "devDataSource")
    public DataSource devDataSource() {
        System.out.println("devDataSource");
        return null;
    }
}
