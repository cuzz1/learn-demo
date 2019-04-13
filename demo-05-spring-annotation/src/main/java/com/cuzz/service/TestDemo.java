package com.cuzz.service;

import org.junit.jupiter.api.Test;

public class TestDemo {
    
    @Test
    //动态代理
    public void fun1(){
        UserService us = new UserServiceImpl();
        
        UserServiceProxyFactory factory = new UserServiceProxyFactory(us);
        
        UserService usProxy = factory.getUserServiceProxy();
        
        usProxy.add();   // 打开事务!
                         // 添加一个user
                         // 提交事务!
    }
}