package com.cuzz.rpc;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.CacheRequest;
import java.net.Socket;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:36
 * @Description: 用于网络处理
 */
public class RpcNetTransport {

    private String host;
    private Integer port;

    public RpcNetTransport(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        System.out.println("begin create socket connect...");
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        try {
            socket = newSocket();
            // 发送
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            // 接受
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object res = inputStream.readObject(); // 反序列化

            // 关闭流
            inputStream.close();
            outputStream.close();
            return res;

        } catch (Exception e) {

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
