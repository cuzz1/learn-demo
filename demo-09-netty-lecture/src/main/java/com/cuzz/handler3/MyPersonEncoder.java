package com.cuzz.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: cuzz
 * @Date: 2019/1/22 16:12
 * @Description:
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol>{

    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder encoder invoked!");
        // 消息头
        out.writeInt(msg.getLength());
        // 消息体
        out.writeBytes(msg.getContent());
    }
}
