package com.cuzz.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Author: cuzz
 * @Date: 2019/1/22 16:04
 * @Description:
 */
public class MyPersonDecoder extends ReplayingDecoder<Void>{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyPersonDecoder decode invoked!");
        // Gets a 32-bit integer at the current {@code readerIndex}
        // and increases the {@code readerIndex} by {@code 4} in this buffer.
        int length = in.readInt();
        byte[] content = new byte[length];
        // Transfers this buffer's data to the specified destination starting at
        // the current {@code readerIndex} and increases the {@code readerIndex}
        // by the number of the transferred bytes (= {@code dst.length}
        in.readBytes(content);

        // 把内容添加到协议中
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(content);

        out.add(personProtocol);

    }
}
