package com.cuzz.byteBuf;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author: cuzz
 * @Date: 2019/1/17 20:45
 * @Description:
 */
public class ByteBufTest01 {
    public static void main(String[] args) {
        final ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0, index = 120; i < 10; i++) {
            buffer.writeByte(index + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(buffer.getByte(i));
        }

    }
}

