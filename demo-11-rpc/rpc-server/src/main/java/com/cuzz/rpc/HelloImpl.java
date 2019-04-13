package com.cuzz.rpc;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:05
 * @Description:
 */
public class HelloImpl implements IHello {
    @Override
    public String sayHello(String msg) {
        return "Hello: " + msg;
    }
}
