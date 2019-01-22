package com.cuzz.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * @Author: cuzz
 * @Date: 2019/1/18 11:01
 * @Description:
 */
public class ByteBufTest02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", Charset.forName("utf-8"));

        // 判断是否为堆缓存，如果是堆缓存，返回true
        if (byteBuf.hasArray()) {
            byte[] bytes = byteBuf.array();
            System.out.println(new String(bytes, Charset.forName("utf-8")));
            System.out.println(byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());


        }
    }
}
