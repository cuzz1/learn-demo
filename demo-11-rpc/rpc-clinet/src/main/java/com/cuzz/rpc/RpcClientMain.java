package com.cuzz.rpc;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:26
 * @Description:
 */
public class RpcClientMain {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IHello hello =  rpcClientProxy.clientProxy(IHello.class, "localhost", 8080);
        System.out.println(hello.sayHello("cuzz"));
    }
}
