package com.cuzz.nio;

import java.beans.FeatureDescriptor;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: cuzz
 * @Date: 2019/1/4 20:43
 * @Description:
 */
public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] bytes = "Hello World!".getBytes();

        for (int i = 0; i < bytes.length; i++) {
            byteBuffer.put(bytes[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
