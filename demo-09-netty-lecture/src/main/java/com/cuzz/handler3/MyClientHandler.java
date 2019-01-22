package com.cuzz.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @Author: cuzz
 * @Date: 2019/1/22 16:25
 * @Description:
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol>{
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收的消息：");
        System.out.println("消息的长度：" + length);
        System.out.println("消息的内容：" + new String(content, Charset.forName("utf-8")));
        System.out.println("客户端接收到的消息数量：" + (++count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String messageToBeSend = "send form client";
            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(messageToBeSend.getBytes("utf-8").length);
            personProtocol.setContent(messageToBeSend.getBytes("utf-8"));
            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
