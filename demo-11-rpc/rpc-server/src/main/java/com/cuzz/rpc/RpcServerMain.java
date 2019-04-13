package com.cuzz.rpc;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:20
 * @Description:
 */
public class RpcServerMain {
    public static void main(String[] args) {
        IHello hello = new HelloImpl();
        RpcServerProxy rpcServerProxy = new RpcServerProxy();
        rpcServerProxy.publisher(hello, 8080);
    }
}
