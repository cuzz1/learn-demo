package com.cuzz.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @Author: cuzz
 * @Date: 2019/1/22 16:16
 * @Description:
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol>{
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到的数据：");
        System.out.println("长度：" + length);
        System.out.println("内容：" + new String(content, Charset.forName("utf-8")));
        System.out.println("服务器接收到的消息数量：" + (++this.count));

        PersonProtocol personProtocol = new PersonProtocol();
        String resp = "hello, world";
        personProtocol.setLength(resp.getBytes("utf-8").length);
        personProtocol.setContent(resp.getBytes("utf-8"));
        ctx.writeAndFlush(personProtocol);
    }
}
