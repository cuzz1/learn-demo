package com.cuzz.rpc;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:13
 * @Description:
 */
public class ProcessorHandler implements Runnable{
    Socket socket;
    Object service;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    public ProcessorHandler(Object service, Socket socket) {
        this.service = service;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("begin Processor handler");
        ObjectInputStream inputStream = null;
        try {
            // 写入
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();

            Object res = invoke(rpcRequest);

            // 写出
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(res);
            outputStream.flush();

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过反射获取对象
     * @param rpcRequest
     * @return
     */
    private Object invoke(RpcRequest rpcRequest) throws Exception{
        // 获取参数
        Object[] params = rpcRequest.getParams();
        // 获取参数的类型
        Class<?>[] types = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            types[i] = params[i].getClass();
        }

        // // 获取class对象
        // Class<?> clazz = Class.forName(rpcRequest.getClassName());
        // // 获取构造器
        // Constructor<?> constructor = clazz.getConstructor(types);
        // // 返回实例
        // return constructor.newInstance(rpcRequest.getParams());

        Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
        return method.invoke(service, params);
    }
}
