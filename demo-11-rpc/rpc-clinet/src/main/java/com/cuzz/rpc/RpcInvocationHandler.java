package com.cuzz.rpc;

import org.omg.CORBA.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:32
 * @Description:
 */
public class RpcInvocationHandler implements InvocationHandler {

    private String host;
    private Integer port;

    public RpcInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        Object res = rpcNetTransport.send(rpcRequest);
        return res;
    }
}
