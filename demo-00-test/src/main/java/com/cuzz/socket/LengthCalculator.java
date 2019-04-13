package com.cuzz.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: cuzz
 * @Date: 2019/2/19 22:44
 * @Description:
 */
public class LengthCalculator extends Thread {

    private Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 获取socket的输出流
            OutputStream os = socket.getOutputStream();
            // 获取socket的输入流
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0 , len);
                System.out.println(new String(bytes, 0 , len));
            }
            // 不要忘记关闭输入输出流
            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
