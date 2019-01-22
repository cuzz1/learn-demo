package com.cuzz.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: cuzz
 * @Date: 2019/1/9 14:24
 * @Description:
 */
public class OldServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true) {
            Socket socket= serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes = new byte[1024];
                while (true) {
                    int readCount = dataInputStream.read(bytes);
                    if (readCount == -1) {
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
