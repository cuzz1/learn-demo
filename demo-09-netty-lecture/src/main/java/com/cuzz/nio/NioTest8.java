package com.cuzz.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: cuzz
 * @Date: 2019/1/5 18:45
 * @Description:
 */
public class NioTest8 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();


        // ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 直接分配
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        for(;;) {
            byteBuffer.clear();
            int len = inputStreamChannel.read(byteBuffer);
            if (len == -1) {
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
