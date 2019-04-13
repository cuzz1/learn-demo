package com.cuzz.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:08
 * @Description: 服务发布
 */
public class RpcServerProxy {

    public void publisher(Object service, int port) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;
        try {
            // 启动一个服务
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(service,socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
