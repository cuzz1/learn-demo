package com.cuzz.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: cuzz
 * @Date: 2019/2/19 22:36
 * @Description:
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        // 创建socket，并将socket绑定到65000端口
        ServerSocket serverSocket = new ServerSocket(65000);
        // 死循环，使socket一直等待并处理客户端发过来的请求
        while (true) {
            // 监听6500端口，直到客户端返回连接信息后才返回
            Socket socket = serverSocket.accept();
            // 获取客户端请求信息后，执行相关逻辑
            new LengthCalculator(socket).start();
        }
    }
}
