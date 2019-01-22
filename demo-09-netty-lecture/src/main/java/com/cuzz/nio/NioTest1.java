package com.cuzz.nio;

import java.nio.IntBuffer;
import java.nio.channels.Channel;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @Author: cuzz
 * @Date: 2019/1/4 20:04
 * @Description:
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);

        for (int i = 0; i < 5; i++) {
            System.out.println(intBuffer);
            intBuffer.put(new SecureRandom().nextInt(10));
        }

        System.out.println("==========");
        intBuffer.flip();
        System.out.println(intBuffer);
        System.out.println("==========");

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer);
            System.out.println(intBuffer.get());
        }
    }
}
