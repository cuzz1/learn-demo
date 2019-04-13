package com.cuzz.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: cuzz
 * @Date: 2019/2/19 23:09
 * @Description:
 */
public class TCPClinet {
    public static void main(String[] args) throws IOException {
        // 创建socket，并指定连接的是ip和端口号
        Socket socket = new Socket("127.0.0.1", 65000);
        // 获取输出流
        OutputStream os = socket.getOutputStream();
        // 获取输入流
        InputStream is = socket.getInputStream();
        os.write("hello world".getBytes());
        int len = 0;
        byte[] bytes = new byte[1024];
        len = is.read(bytes);
        String content = new String(bytes, 0, len);
        System.out.println(content);
        is.close();
        os.close();
        socket.close();
    }
}
