package com.cuzz.test02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: cuzz
 * @Date: 2019/2/24 20:37
 * @Description:
 */
public class DynamicProxy implements InvocationHandler{
    IHello hello;

    public DynamicProxy(IHello hello) {
        this.hello = hello;
    }

    public Object getProxyObject() {
        Object originObject = Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), Hello.class.getInterfaces(), this);
        return originObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Cuzz say:");
        return method.invoke(hello, args);
    }

    public static void main(String[] args) {
       IHello hello = (IHello) new DynamicProxy(new Hello()).getProxyObject();

       hello.sayHello();
    }
}
