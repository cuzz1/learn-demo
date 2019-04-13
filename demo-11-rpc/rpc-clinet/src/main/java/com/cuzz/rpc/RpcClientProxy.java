package com.cuzz.rpc;

import java.lang.reflect.Proxy;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:28
 * @Description:
 */
public class RpcClientProxy {
    public <T> T clientProxy(Class<?> interfaceCls, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[]{interfaceCls},
                new RpcInvocationHandler(host, port));
    }
}
